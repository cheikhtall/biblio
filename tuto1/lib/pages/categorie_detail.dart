import 'package:flutter/material.dart';
import 'package:tuto1/auth/auth_repository.dart';
import 'package:tuto1/models/LivreModel.dart';
import 'package:tuto1/models/category_model.dart';

import '../_utils/colors.dart';

class CategorieDetails extends StatefulWidget {
  // const LivreDetails({Key? key}) : super(key: key);

  final int categoryId;
  CategorieDetails({required this.categoryId});
  @override
  _CategorieDetailsState createState() => _CategorieDetailsState();
}

class _CategorieDetailsState extends State<CategorieDetails> {
  final AuthRepository authRepository = AuthRepository();
  Future<CategoryModel>? _categorieFuture;

  @override
  void initState() {
    super.initState();
    _categorieFuture = authRepository.getCategorieById(widget.categoryId);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          backgroundColor: mainColor,
          title: Text('Détails de la catégorie'),
        ),
        body: FutureBuilder<CategoryModel>(
          future: _categorieFuture,
          builder: (BuildContext context, AsyncSnapshot<CategoryModel> snapshot) {
            if(snapshot.connectionState == ConnectionState.waiting){
              return Center(
                child: CircularProgressIndicator(),
              );
            } else if(snapshot.hasError){
              return Center(
                child: Text("Erreur aucune donnée trouvéé"),
              );
            } else if (snapshot.hasData){
              final CategoryModel categoryModel = snapshot.data!;
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
                              height: 15,
                            ),
                            Text(
                              //widget.planetInfo!.name.toString(),
                              categoryModel.name!.toUpperCase(),
                              style: TextStyle(
                                  fontSize: 40,
                                  fontFamily: 'Avenir',
                                  color: mainColor,
                                  fontWeight: FontWeight.w900),
                              textAlign: TextAlign.left,
                            ),
                            const SizedBox(
                              height: 10,
                            ),
                            Text(
                              "Description",
                              style: TextStyle(
                                  fontSize: 40,
                                  fontFamily: 'Avenir',
                                  color: Colors.black,
                                  fontWeight: FontWeight.w300),
                              textAlign: TextAlign.left,
                            ),
                            const Divider(
                              color: Colors.black38,
                            ),
                            const SizedBox(
                              height: 10,
                            ),
                            Container(
                              height: 140,
                              width: MediaQuery.of(context).size.width * 0.95,
                              child: SingleChildScrollView(
                                physics: const BouncingScrollPhysics(),
                                scrollDirection: Axis.vertical,
                                child: Text(
                                  categoryModel.description!, //widget.planetInfo!.description.toString(),
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
