import 'package:flutter/material.dart';
import 'package:email_validator/email_validator.dart';
import 'package:tuto1/_utils/colors.dart';
import 'package:tuto1/auth/auth_repository.dart';
import '../pages/bottom_bar.dart';
import 'component/custom_form_button.dart';
import 'component/page_header.dart';
import 'component/page_heading.dart';

class LoginPage extends StatefulWidget {
  const LoginPage({Key? key}) : super(key: key);

  @override
  State<LoginPage> createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {

  final _loginFormKey = GlobalKey<FormState>();
  final _usernameController = TextEditingController();
  final _passwordController = TextEditingController();
  final _loginAuthRepository = AuthRepository();
  @override
  void dispose() {
    _usernameController.dispose();
    _passwordController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    Size size = MediaQuery.of(context).size;
    return SafeArea(
      child: Scaffold(
        backgroundColor: const Color(0xFFFF5656),
        body: Column(
          children: [
            const PageHeader(),
            Expanded(
              child: Container(
                decoration: const BoxDecoration(
                  color: Colors.white,
                  borderRadius: BorderRadius.vertical(top: Radius.circular(30),),
                ),
                child: SingleChildScrollView(
                  child: Form(
                    key: _loginFormKey,
                    child: Column(
                      children: [
                        const PageHeading(title: 'Log-in',),
                        TextFormField(
                            controller: _usernameController,
                            decoration: InputDecoration(
                              labelText: 'Date de naissance',
                              hintText: "Votre email",
                            ),
                            validator: (textValue) {
                              if(textValue == null || textValue.isEmpty) {
                                return 'Email obligatoire!';
                              }
                              if(!EmailValidator.validate(textValue)) {
                                return 'entrez votre email';
                              }
                              return null;
                            }
                        ),
                        const SizedBox(height: 16,),
                        TextFormField(
                          controller: _passwordController,
                          obscureText: true,
                          decoration: InputDecoration(
                            labelText: 'Mot de passe',
                            hintText: "Mot de passe",
                              suffixIconConstraints: BoxConstraints(
                                minWidth: 2,
                                minHeight: 2,
                              ),
                              suffixIcon: InkWell(
                                  child: Icon(Icons.clear, size: 14), onTap: () {
                                    setState(() {

                                    });
                              })
                          ),
                          validator: (textValue) {
                            if(textValue == null || textValue.isEmpty) {
                              return 'Mot de passe requis!';
                            }
                            return null;
                          },
                        ),
                        const SizedBox(height: 16,),
                        Container(
                          width: size.width * 0.80,
                          alignment: Alignment.centerRight,
                          child: GestureDetector(
                            onTap: () => {
                              print("vous avez oublié le mot de passe")
                            },
                            child: const Text(
                              'Mot de passe oublié?',
                              style: TextStyle(
                                color: mainColor,
                                fontSize: 13,
                                fontWeight: FontWeight.bold,
                              ),
                            ),
                          ),
                        ),
                        const SizedBox(height: 20,),
                        CustomFormButton(innerText: 'Login', onPressed: () async {
                          if (_loginFormKey.currentState!.validate()) {
                            final username = _usernameController.text;
                            final password = _passwordController.text;
                            final success = await _loginAuthRepository.authenticate(username, password);
                            if (success!=null) {
                              ScaffoldMessenger.of(context).showSnackBar(
                                const SnackBar(content: Text('Bienvenue..')),
                              );
                              Navigator.push(context, MaterialPageRoute(builder: (context) => MyHomePage()));
                            }
                            else {
                              showDialog(
                                context: context,
                                builder: (context) => AlertDialog(
                                  title: Text("Erreur"),
                                  content: Text("Email ou mot de passe incorrect."),
                                  actions: <Widget>[
                                    TextButton(
                                      onPressed: () {
                                        Navigator.pop(context);
                                      },
                                      child: Text("OK"),
                                    ),
                                  ],
                                ),
                              );
                            }
                          }
                        } ),
                        const SizedBox(height: 18,),
                        SizedBox(
                          width: size.width * 0.8,
                          child: Row(
                            mainAxisAlignment: MainAxisAlignment.center,
                            children: [
                              const Text('Pas encore de compte? ', style: TextStyle(fontSize: 13, color: Color(0xff748288), fontWeight: FontWeight.bold),),
                              GestureDetector(
                                onTap: () => {
                                  //Navigator.push(context, MaterialPageRoute(builder: (context) => const SignupPage()))
                                  print("Vous allez créer un compte")
                                },
                                child: const Text('Sign-up', style: TextStyle(fontSize: 15, color: mainColor, fontWeight: FontWeight.bold),),
                              ),
                            ],
                          ),
                        ),
                        const SizedBox(height: 20,),
                      ],
                    ),
                  ),
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }

}