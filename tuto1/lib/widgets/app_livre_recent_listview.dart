import 'package:flutter/material.dart';
import 'package:tuto1/_utils/colors.dart';
import 'package:tuto1/auth/auth_repository.dart';

import '../pages/livre-details.dart';

class AppLivreRecentListView extends StatefulWidget {
  const AppLivreRecentListView({Key? key}) : super(key: key);
  @override
  State<AppLivreRecentListView> createState() => _AppLivreRecentListViewState();
}

class _AppLivreRecentListViewState extends State<AppLivreRecentListView> {
  bool recent = true;
  late Future<List<dynamic>> futureList;
  @override
  void initState() {
    super.initState();
    // Simuler une charge asynchrone des données
    Future.delayed(Duration(seconds: 2), () {
      setState(() {
        recent = true;
      });
    });
    futureList = AuthRepository().getLivresByRecent(recent);
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Container(
          padding: const EdgeInsets.all(15),
          child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: const[
              Text(
                "Livres Récents",
                style: TextStyle(
                    fontWeight: FontWeight.w900,
                    fontSize: 24
                ),
              ),
            ],
          ),
        ),
        SizedBox(
          height: 200,
          child: FutureBuilder<List<dynamic>>(
            future: futureList,
            builder: (BuildContext context, AsyncSnapshot<List<dynamic>> snapshot) {
              if (snapshot.connectionState == ConnectionState.waiting) {
                return Center(child: CircularProgressIndicator(
                  valueColor: new AlwaysStoppedAnimation<Color>(mainColor),
                ),);
              } else if (snapshot.hasError) {
                return Center(child: Text('Une erreur s\'est produite'));
              } else if (!snapshot.hasData || snapshot.data!.isEmpty) {
                return Center(child: Text('Aucune donnée correspondante!!'));
              } else {
                return
                    ListView.builder(
                      scrollDirection: Axis.horizontal,
                      padding: const EdgeInsets.only(left: 10, top: 20, right: 10, bottom: 10),
                      itemCount: snapshot.data?.length,
                      itemBuilder: (context, index) {
                        final data = snapshot.data![index];
                        return GestureDetector(
                          onTap:(){
                            Navigator.push(context, MaterialPageRoute(builder: (context) => LivreDetails(livreId: data.id),
                            ),);
                          },
                          child:
                          Container(
                            alignment: Alignment.bottomLeft,
                            padding: const EdgeInsets.all(20),
                            margin: const EdgeInsets.all(20),
                            width: 150,
                            decoration: BoxDecoration(
                                image: DecorationImage(
                                  image: NetworkImage('https://sa.kapamilya.com/absnews/abscbnnews/media/2021/afp/01/17/20210116-mt-semeru-indonesia-ash-afp-s.jpg',
                                  ),
                                  fit: BoxFit.cover,
                                ),
                                borderRadius: BorderRadius.circular(10)
                            ),
                            child: Column(
                              mainAxisAlignment: MainAxisAlignment.end,
                              crossAxisAlignment: CrossAxisAlignment.start,
                              children: [
                                Text("Titre : ${data.title}",  style: TextStyle(fontSize: 18, color: Colors.white),),
                               // Text("Auteur : ${data.nomAuteur}",  style: TextStyle(fontSize: 18, color: Colors.white),),
                              ],
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
    );
  }
}



// import 'package:flutter/material.dart';
// import 'package:tuto1/models/mount_model.dart';
//
// class AppLivreRecentListView extends StatelessWidget {
//   const AppLivreRecentListView({Key? key}) : super(key: key);
//
//   @override
//   Widget build(BuildContext context) {
//     return Column(
//       children: [
//         Container(
//           padding: const EdgeInsets.all(15),
//           child: Row(
//             mainAxisAlignment: MainAxisAlignment.spaceBetween,
//             children: const[
//               Text(
//                 "Livres recents",
//                 style: TextStyle(
//                     fontWeight: FontWeight.w900,
//                     fontSize: 24
//                 ),
//               ),
//             ],
//           ),
//         ),
//         SizedBox(
//           height: 150,
//           child: ListView.builder(
//               scrollDirection: Axis.horizontal,
//               itemCount: mountItems.length,
//               itemBuilder: (context, index){
//                 final currentMount = mountItems[index];
//                 return Container(
//                   alignment: Alignment.bottomLeft,
//                   padding: const EdgeInsets.all(20),
//                   margin: const EdgeInsets.all(20),
//                   width: 150,
//                   decoration: BoxDecoration(
//                     image: DecorationImage(
//                       image: NetworkImage(currentMount.path),
//                       fit: BoxFit.cover,
//                     ),
//                     borderRadius: BorderRadius.circular(16)
//                   ),
//                   child: Column(
//                     mainAxisAlignment: MainAxisAlignment.end,
//                     crossAxisAlignment: CrossAxisAlignment.start,
//                     children: [
//                       Text(currentMount.name,
//                       style: const TextStyle(
//                         color: Colors.white,
//                         fontWeight: FontWeight.bold
//                       ),
//                       ),
//                       Text(
//                         currentMount.location,
//                         style: const TextStyle(color: Colors.white),
//                       )
//                     ],
//                   ),
//                 );
//               }
//           ),
//         ),
//       ],
//     );
//   }
// }
