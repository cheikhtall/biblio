import 'package:flutter/material.dart';
import 'package:jwt_decoder/jwt_decoder.dart';
import 'package:tuto1/_utils/colors.dart';
import 'package:tuto1/auth/auth_repository.dart';
import 'package:tuto1/login/login_page.dart';
import 'package:tuto1/pages/update_profile.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';

import '../models/adherant_model.dart';
import '../widgets/drawer_widget.dart';
import '../widgets/profile_menu.dart';

class ProfileScreen extends StatefulWidget {
  const ProfileScreen({Key? key}) : super(key: key);

  @override
  State<ProfileScreen> createState() => _ProfileScreenState();
}

class _ProfileScreenState extends State<ProfileScreen> {
   AdherantModel? adherantModel;
  final AuthRepository authRepository = AuthRepository();
  @override
  void initState() {
    super.initState();
    decodeToken();
  }
  final _storage = FlutterSecureStorage();
  String decodedEmail="";
  String decodedPrenom = "";
  String decodedNom = "";

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        elevation: 0,
        backgroundColor: Colors.transparent,
        title: const Center(
          child: Icon(
            Icons.terrain,
            color: mainColor,
            size: 40,
          ),
        ),
        actions: const[
          SizedBox(width: 30, height: 30,),
        ],
        iconTheme: const IconThemeData(color: mainColor),
      ),
      drawer: Drawer(
        child: DrawerWidget(),
      ),
      body: SingleChildScrollView(
        child: Container(
          padding: const EdgeInsets.all(20),
          child: Column(
            children: [
              /// -- IMAGE
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
                      decoration: BoxDecoration(borderRadius: BorderRadius.circular(100), color: mainColor),
                      child: const Icon(
                        Icons.pending,
                        color: Colors.black,
                        size: 20,
                      ),
                    ),
                  ),
                ],
              ),
              const SizedBox(height: 10),
              Text("Votre profil", style: Theme.of(context).textTheme.headline4),
              Text("Adhérant", style: Theme.of(context).textTheme.bodyText2),
              const SizedBox(height: 20),

              /// -- BUTTON
              SizedBox(
                width: 300,
                child: ElevatedButton(
                  onPressed: () => {Navigator.push(context, MaterialPageRoute(builder: (context) => UpdateProfileScreen()))},
                  style: ElevatedButton.styleFrom(backgroundColor: mainColor,
                      padding:
                      const EdgeInsets.symmetric(horizontal: 30, vertical: 15),
                      textStyle:
                      const TextStyle(fontSize: 20, fontWeight: FontWeight.bold),
                      side: BorderSide.none, shape: const StadiumBorder()
                  ),
                  child: const Text("Modifier le Profil", style: TextStyle(color: Colors.white)),
                ),
              ),
              const SizedBox(height: 10),
              const Divider(),
              const SizedBox(height: 10),

              /// -- MENU
              ProfileMenuWidget(title: "${adherantModel?.email}", icon: Icons.mail_outline, onPress: () {}),
              ProfileMenuWidget(title: "${adherantModel?.firstname}", icon: Icons.person_outline, onPress: () {}),
              ProfileMenuWidget(title: "${adherantModel?.lastName}", icon: Icons.person, onPress: () {}),
              ProfileMenuWidget(title: "${adherantModel?.phone}", icon: Icons.phone, onPress: () {}),
              ProfileMenuWidget(title: "${adherantModel?.address}", icon: Icons.add_location, onPress: () {}),
              ProfileMenuWidget(title: "${adherantModel?.birth}", icon: Icons.calendar_month, onPress: () {}),
              const Divider(),
              const SizedBox(height: 10),
              ProfileMenuWidget(
                  title: "Deconnexion",
                  icon: Icons.outbox_outlined,
                  textColor: Colors.red,
                  endIcon: false,
                  onPress: () {
                    showDialog(
                      context: context,
                      builder: (BuildContext context) {
                        return AlertDialog(
                          title: const Text("Déconnexion"),
                          content: const Text("Êtes-vous sûr de vouloir vous déconnecter ?"),
                          actions: [
                            TextButton(
                              onPressed: () {
                                Navigator.of(context).pop();
                              },
                              child: const Text("Non"),
                            ),
                            TextButton(
                              onPressed: () async {
                                await authRepository.logout();
                                Navigator.push(context, MaterialPageRoute(builder: (context) => LoginPage()));
                              },
                              child: const Text("Oui"),
                            ),
                          ],
                        );
                      },
                    );
                  }),
        ]
      )
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
    if (decodedEmail != null) {
      AdherantModel? adherant = await authRepository.getAdherantByEmail(email!);
      setState(() {
        adherantModel = adherant;
      });
    }
  }
}