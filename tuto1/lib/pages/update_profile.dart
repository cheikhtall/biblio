import 'package:flutter/material.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:jwt_decoder/jwt_decoder.dart';
import 'package:tuto1/_utils/colors.dart';
import 'package:tuto1/pages/profile-page.dart';

import '../auth/auth_repository.dart';
import '../models/adherant_model.dart';

class UpdateProfileScreen extends StatefulWidget {
  const UpdateProfileScreen({Key? key}) : super(key: key);

  @override
  State<UpdateProfileScreen> createState() => _UpdateProfileScreenState();
}

class _UpdateProfileScreenState extends State<UpdateProfileScreen> {

  AdherantModel? adherantModel;
  int idAdherant = 0;
  final AuthRepository authRepository = AuthRepository();
  @override
  void initState() {
    super.initState();
    decodeToken();
  }
  void handleTokenDecoded() {
    setState(() {
      _emailController.text = decodedEmail;
      _birthController.text= "${adherantModel?.birth}";
      _telephoneController.text= "${adherantModel?.phone}";
      _adresseController.text= "${adherantModel?.address}";
    });
  }
  final _storage = FlutterSecureStorage();
  String decodedEmail="";
  String decodedPrenom = "";
  String decodedNom = "";

  final _updateProfileFormKey = GlobalKey<FormState>();
  final _emailController = TextEditingController();
  final _telephoneController = TextEditingController();
  final _adresseController = TextEditingController();
  final _firstnameController = TextEditingController();
  final _lastnameController = TextEditingController();
  final _birthController = TextEditingController();

  @override
  void dispose() {
    _emailController.dispose();
    _telephoneController.dispose();
    _adresseController.dispose();
    _firstnameController.dispose();
    _lastnameController.dispose();
    _birthController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    //final controller = Get.put(ProfileController());
    return Scaffold(
      appBar: AppBar(
        automaticallyImplyLeading: false,
        backgroundColor: mainColor,
        leading: IconButton(
            onPressed: () => {Navigator.pop(context, MaterialPageRoute(builder: (context) => ProfileScreen()))},
            icon: const Icon(Icons.arrow_back)),
        title: Text("Modifier le profil", style: Theme.of(context).textTheme.headline4),
      ),
      body: SingleChildScrollView(
        child: Container(
          padding: const EdgeInsets.all(20),
          child: Column(
            children: [
              // -- IMAGE with ICON
              Stack(
                children: [
                  SizedBox(
                    width: 120,
                    height: 120,
                    child: CircleAvatar(
                        backgroundColor: Colors.transparent,
                        child: const Image(image: AssetImage("assets/images/profile.png")
                        )
                    ),
                  ),
                  Positioned(
                    bottom: 0,
                    right: 0,
                    child: Container(
                      width: 35,
                      height: 35,
                      decoration:
                      BoxDecoration(borderRadius: BorderRadius.circular(100), color: mainColor),
                      child: const Icon(Icons.add_photo_alternate_outlined, color: Colors.black, size: 20),
                    ),
                  ),
                ],
              ),
              const SizedBox(height: 40),

              // -- Form Fields
              Form(
                key: _updateProfileFormKey,
                child: Column(
                  children: [
                    TextFormField(
                      controller: _emailController,
                      decoration: const InputDecoration(
                          label: Text("Email"), prefixIcon: const Icon(Icons.mail_outline)),
                    ),
                    const SizedBox(height: 15),
                    TextFormField(

                      controller: _telephoneController,
                      decoration: const InputDecoration(
                          label: Text("Telephone"), prefixIcon: const Icon(Icons.phone)),
                    ),
                    const SizedBox(height:15),
                    TextFormField(
                      controller: _adresseController,
                      decoration: const InputDecoration(
                          label: Text("Adresse"), prefixIcon: const Icon(Icons.add_location)),
                    ),
                    TextFormField(
                      enabled: false,
                      controller: _birthController,
                      decoration: const InputDecoration(
                          label: Text("Birth"), prefixIcon: const Icon(Icons.calendar_month)),
                    ),
                    const SizedBox(height: 50),

                    // -- Form Submit Button
                    SizedBox(
                      width: double.infinity,
                      child: ElevatedButton(
                        //onPressed: () => Get.to(() => const UpdateProfileScreen()),
                        onPressed: ()=>{
                          _updateProfile()
                        },
                        style: ElevatedButton.styleFrom(
                            backgroundColor: mainColor,
                            side: BorderSide.none,
                            shape: const StadiumBorder()),
                        child: const Text("Modifier", style: TextStyle(color: Colors.black)),
                      ),
                    ),
                    const SizedBox(height: 25),
                  ],
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }

  void decodeToken() async{
    final token = await _storage.read(key: 'jwt_token') ;
    Map<String, dynamic> decodedToken = JwtDecoder.decode(token!);
    // Accédez aux informations du token décodé
    String email = decodedToken['email'];
    String firstname = decodedToken['given_name'];
    String lastname = decodedToken['family_name'];
    setState(() {
      decodedEmail = email;
      decodedPrenom = firstname;
      decodedNom = lastname;
    });
    print("decode email : "+email);
    if (decodedEmail != null) {
      AdherantModel? adherant = await authRepository.getAdherantByEmail(email!);
      setState(() {
        adherantModel = adherant;
        idAdherant = adherant.id!;
      });
      print("ID ADHERANT : ${idAdherant}");
    }
    handleTokenDecoded();
  }

  void _updateProfile() async {
    // Récupérez les valeurs mises à jour depuis les contrôleurs de texte
    String updatedEmail = _emailController.text;
    String updatedTelephone = _telephoneController.text;
    String updatedAdresse = _adresseController.text;
    String updatedBirth = _birthController.text;

    // Créez un objet de données pour la mise à jour du profil
    AdherantModel updatedProfile = AdherantModel(
      id: idAdherant,
      firstname: decodedNom,
      lastName: decodedPrenom,
      email: updatedEmail,
      phone: updatedTelephone,
      address: updatedAdresse,
      birth: updatedBirth,
    );

    try {
      await authRepository.updateAdherant(idAdherant, updatedProfile);
      print('Profil mis à jour avec succès');
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(
          content: Text('Profil mis à jour avec succès'),
          backgroundColor: Colors.green,
          duration: const Duration(seconds: 2),
          behavior: SnackBarBehavior.floating,
        ),
      );
      Navigator.push(context, MaterialPageRoute(builder: (context) => ProfileScreen()));
    } catch (error) {
      print('Échec de la mise à jour du profil : $error');
      // Vous pouvez ajouter une logique supplémentaire ici, par exemple, afficher un message d'erreur à l'utilisateur
    }
  }

}