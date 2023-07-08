import 'package:flutter/material.dart';
import 'package:tuto1/auth/auth_repository.dart';
import 'package:tuto1/pages/livre-details.dart';

import '../_utils/colors.dart';
import '../widgets/drawer_widget.dart';

class YourListView extends StatefulWidget {
  const YourListView({Key? key}) : super(key: key);

  @override
  _YourListViewState createState() => _YourListViewState();
}

class _YourListViewState extends State<YourListView> {
  late Future<List<dynamic>> futureList;
  bool isLoading = true;
  String texte = "";
  TextEditingController searchController = TextEditingController();
  @override
  void initState() {
    super.initState();
    // Simuler une charge asynchrone des données
    Future.delayed(Duration(seconds: 2), () {
      setState(() {
        isLoading = false;
      });
    });
    futureList = AuthRepository().getLivresByTitle(texte);
  }

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

      body: Column(
        children: [
            Row(
              children: [
                Expanded(
                  child: Container(
                    padding: EdgeInsets.only(left: 20, right: 10),
                    child: TextFormField(
                      controller: searchController,
                      decoration: InputDecoration(
                        hintText: "Rechercher...",
                        border: OutlineInputBorder(
                          borderSide: BorderSide(color: mainColor, width: 2),
                          borderRadius: BorderRadius.circular(10),
                        ),
                      ),
                    ),
                  ),
                ),
                IconButton(
                    padding: EdgeInsets.only(right: 10),
                    onPressed: (){
                      setState(() {
                        this.texte = searchController.text;
                        futureList = AuthRepository().getLivresByTitle(texte);
                      });
                    }, icon: Icon(Icons.search, color: mainColor,))],
          ),
          Expanded(
            child: FutureBuilder<List<dynamic>>(
              future: futureList,
              builder: (BuildContext context, AsyncSnapshot<List<dynamic>> snapshot) {
                if (snapshot.connectionState == ConnectionState.waiting) {
                  return Center(child: CircularProgressIndicator(
                    valueColor: new AlwaysStoppedAnimation<Color>(mainColor),
                  ),);
                } else if (snapshot.hasError) {
                  return Center(child: Text('Mettez le titre du livre'));
                } else if (!snapshot.hasData || snapshot.data!.isEmpty) {
                  return Center(child: Text('Aucune donnée correspondante!!'));
                } else {
                  return
                     ListView.builder(
                    padding: const EdgeInsets.only(left: 10, top: 20, right: 10, bottom: 10),
                    itemCount: snapshot.data?.length,
                    itemBuilder: (context, index) {
                      final data = snapshot.data![index];
                      return GestureDetector(
                        onTap:(){
                          Navigator.push(context, MaterialPageRoute(builder: (context) => LivreDetails(livreId: data.id),
                          ),);
                          },
                        child: Container(
                          width: double.infinity,
                          height: 111,
                          padding: const EdgeInsets.symmetric(vertical: 18, horizontal: 10),
                          margin: const EdgeInsets.symmetric(vertical: 10, horizontal: 22),
                          decoration: BoxDecoration(
                              borderRadius: BorderRadius.circular(10),
                              border: Border.all(color: Colors.green.withOpacity(0.12), width: 1),
                              color: Colors.white),
                          child: ListTile(
                            title: Row(
                              children: [
                                ClipRRect(
                                    borderRadius: BorderRadius.circular(15),
                                    child: Image.asset(
                                      "assets/images/livre.png",
                                      width: 75,
                                      height: 75,
                                      fit: BoxFit.cover,
                                    )),
                                const SizedBox(
                                  width: 16,
                                ),
                                Expanded(
                                    child: Column(
                                      children: [
                                        Text("Titre : ${data.title}",  style: TextStyle(fontSize: 18, color: Colors.black),),
                                        Text("Auteur : ${data.nomAuteur}",  style: TextStyle(fontSize: 18, color: Colors.black),),
                                      ],
                                    )
                                ),
                              ],
                            ),
                            // Ajoutez ici les autres éléments de votre carte
                          ),
                        ),
                      );
                    },
                  );
                }
                // return CircularProgressIndicator(
                //   valueColor: new AlwaysStoppedAnimation<Color>(mainColor),
                // );
              },
            ),
          ),
        ],
      ),
    );
  }
}
