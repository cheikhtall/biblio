import 'package:flutter/material.dart';
import 'package:jwt_decoder/jwt_decoder.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';

import '../_utils/colors.dart';
import '../auth/auth_repository.dart';
import '../models/adherant_model.dart';
class AppHeader extends StatefulWidget {
  const AppHeader({Key? key}) : super(key: key);

  @override
  State<AppHeader> createState() => _AppHeaderState();
}

class _AppHeaderState extends State<AppHeader> {
  AdherantModel? adherantModel;
  final AuthRepository authRepository = AuthRepository();
  final _storage = FlutterSecureStorage();

  @override
  void initState() {
    super.initState();
    decodeToken();
  }
  String decodedEmail="";
  String decodedPrenom = "";
  String decodedNom = "";

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.only(left: 30, top: 30, right: 30),
      child: Row(
        children: [
          ClipOval(
            child: Image.network('https://avatars.githubusercontent.com/u/14922088?v=4',
            width: 50,
            height: 50,
            fit: BoxFit.cover,
            ),
          ),
          const SizedBox(width: 16),
          Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Text(
                "Bonjour ${adherantModel?.firstname}",
                style:
                TextStyle(color: Colors.black, fontWeight: FontWeight.bold),
              ),
              Text(
                'Bienvenue',
                style: TextStyle(color: mainColor, fontSize: 12),
              ),
            ],
          ),
        ],
      ),
    );
  }

  void decodeToken() async {
    final token = await _storage.read(key: 'jwt_token');
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
