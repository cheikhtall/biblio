import 'package:flutter/material.dart';
import 'package:tuto1/_utils/colors.dart';

import '../pages/update_profile.dart';

class DrawerWidget extends StatelessWidget {
  const DrawerWidget({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Drawer(
        child: ListView(
          padding: EdgeInsets.zero,
          children: [
            DrawerHeader(
              decoration: BoxDecoration(
                color: mainColor,
              ),
              child: Text(
                "Menu",
                style: TextStyle(
                  color: Colors.white,
                  fontSize: 24,
                ),
              ),
            ),
            ListTile(
              leading: Icon(Icons.read_more),
              title: Text("Deja Lu"),
              onTap: () {
                Navigator.of(context).pop();
                Navigator.push(context, MaterialPageRoute(builder: (context) => UpdateProfileScreen()));
                // Ajoutez ici le code pour naviguer vers la page d'accueil
              },
            ),
            ListTile(
              leading: Icon(Icons.access_alarm),
              title: Text("En Attente"),
              onTap: () {
                Navigator.pop(context);
                // Ajoutez ici le code pour naviguer vers la page de paramètres
              },
            ),
            ListTile(
              leading: Icon(Icons.add_task),
              title: Text("Valide"),
              onTap: () {
                Navigator.pop(context);
                // Ajoutez ici le code pour naviguer vers la page de paramètres
              },
            ),
          ],
        ),
    );
  }
}


