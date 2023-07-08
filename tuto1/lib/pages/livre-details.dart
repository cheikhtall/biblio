import 'package:flutter/material.dart';
import 'package:tuto1/auth/auth_repository.dart';
import 'package:tuto1/models/LivreModel.dart';

import '../_utils/colors.dart';

class LivreDetails extends StatefulWidget {
 // const LivreDetails({Key? key}) : super(key: key);

  final int livreId;
  LivreDetails({required this.livreId});
  @override
  _LivreDetailsState createState() => _LivreDetailsState();
}

class _LivreDetailsState extends State<LivreDetails> {
  final AuthRepository authRepository = AuthRepository();
  Future<LivreModel>? _livreFuture;

  @override
  void initState() {
    super.initState();
    _livreFuture = authRepository.getLivreById(widget.livreId);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(

      appBar: AppBar(
        backgroundColor: mainColor,
        title: Text('Détails du livre'),
      ),
      body: FutureBuilder<LivreModel>(
        future: _livreFuture,
        builder: (BuildContext context, AsyncSnapshot<LivreModel> snapshot) {
          if(snapshot.connectionState == ConnectionState.waiting){
            return Center(
              child: CircularProgressIndicator(),
            );
          } else if(snapshot.hasError){
            return Center(
              child: Text("Erreur aucune donnée trouvéé"),
            );
          } else if (snapshot.hasData){
            final LivreModel livreModel = snapshot.data!;
            return SafeArea(
              child: Stack(
                children: <Widget>[
                  Padding(padding: const EdgeInsets.only(left: 20.0, right: 20.0, top: 32),
                    child: SingleChildScrollView(
                      physics: const BouncingScrollPhysics(),
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          const SizedBox(
                            height: 10,
                          ),
                          Center(
                            child: Container(
                                width: 200,
                                height: 200,
                                child: Image.network("https://resize.elle.fr/article_1280/var/plain_site/storage/images/loisirs/livres/news/voici-le-livre-le-plus-vendu-en-france-en-2022-et-ce-n-est-pas-un-roman-4085975/98031195-1-fre-FR/Voici-le-livre-le-plus-vendu-en-France-en-2022-et-ce-n-est-pas-un-roman.jpg")),
                          ),
                          const SizedBox(
                            height: 10,
                          ),
                          Text(
                            //widget.planetInfo!.name.toString(),
                            livreModel.title!,
                            style: TextStyle(
                                fontSize: 30,
                                fontFamily: 'Avenir',
                                color: mainColor,
                                fontWeight: FontWeight.w900),
                            textAlign: TextAlign.left,
                          ),
                          const SizedBox(
                            height: 10,
                          ),
                          Text(
                            "Nom de l'Auteur: ${livreModel.nomAuteur!}",
                            style: TextStyle(
                                fontSize: 20,
                                fontFamily: 'Avenir',
                                color: Colors.black,
                                fontWeight: FontWeight.w300),
                            textAlign: TextAlign.left,
                          ),
                          const SizedBox(
                            height: 10,
                          ),
                          Text(
                            "Date de publication: ${livreModel.dateParrution!}",
                            style: TextStyle(
                                fontSize: 20,
                                fontFamily: 'Avenir',
                                color: Colors.black,
                                fontWeight: FontWeight.w300),
                            textAlign: TextAlign.left,
                          ),
                          const SizedBox(
                            height: 10,
                          ),
                          Text(
                            "Nombre Disponible: ${livreModel.nombreDisponible!}",
                            style: TextStyle(
                                fontSize: 20,
                                fontFamily: 'Avenir',
                                color: Colors.black,
                                fontWeight: FontWeight.w300),
                            textAlign: TextAlign.left,
                          ),
                          const SizedBox(
                            height: 10,
                          ),
                          Text(
                            "Localisation: ${livreModel.localisation!}",
                            style: TextStyle(
                                fontSize: 20,
                                fontFamily: 'Avenir',
                                color: Colors.black,
                                fontWeight: FontWeight.w300),
                            textAlign: TextAlign.left,
                          ),
                          const SizedBox(
                            height: 10,
                          ),
                          Text(
                            "Description",
                            style: TextStyle(
                                fontSize: 20,
                                fontFamily: 'Avenir',
                                color: Colors.black,
                                fontWeight: FontWeight.w300),
                            textAlign: TextAlign.left,
                          ),
                          const Divider(
                            color: Colors.black38,
                          ),
                          const SizedBox(
                            height: 30,
                          ),
                          Container(
                            height: 140,
                            width: MediaQuery.of(context).size.width * 0.95,
                            child: SingleChildScrollView(
                              physics: const BouncingScrollPhysics(),
                              scrollDirection: Axis.vertical,
                              child: Text(
                                  livreModel.decription!, //widget.planetInfo!.description.toString(),
                                style: TextStyle(
                                    fontSize: 20,
                                    overflow: TextOverflow.ellipsis,
                                    fontFamily: 'Avenir',
                                    color: Colors.grey,
                                    fontWeight: FontWeight.w400),
                                textAlign: TextAlign.left,
                                maxLines: 60,
                              ),
                            ),
                          ),
                          //SizedBox(height: 10,),
                          Center(
                            child: SizedBox(
                              width: 200,
                              child: ElevatedButton(
                                onPressed: () => {print("ok!!!")},
                                style: ElevatedButton.styleFrom(backgroundColor: mainColor,
                                    padding:
                                    const EdgeInsets.symmetric(horizontal: 30, vertical: 15),
                                    textStyle:
                                    const TextStyle(fontSize: 20, fontWeight: FontWeight.bold),
                                    side: BorderSide.none, shape: const StadiumBorder()
                                ),
                                child: const Text("Emprunter", style: TextStyle(color: Colors.white)),
                              ),
                            ),
                          ),
                        ],
                      ),
                    ),
                  ),
                ],
              ),
            );
          } else {
            return Center(
              child: Text('Aucun livre trouvé'),
            );
          }
        },
      )
    );
  }
}
