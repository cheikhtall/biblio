import 'package:flutter/material.dart';
import 'package:tuto1/_utils/colors.dart';
import 'package:tuto1/pages/categorie_detail.dart';
import '../auth/auth_repository.dart';

class AppCategoryList extends StatefulWidget {
  const AppCategoryList({Key? key}) : super(key: key);

  @override
  _AppCategoryListState createState() => _AppCategoryListState();
}

class _AppCategoryListState extends State<AppCategoryList> {
  bool isLoading = true;
  late Future<List<dynamic>> futureList;
  @override
  void initState() {
    super.initState();
    // Simuler une charge asynchrone des données
    Future.delayed(Duration(seconds: 1), () {
      setState(() {
        isLoading = false;
      });
    });
    futureList = AuthRepository().getCategories();
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
                "Categories",
                style: TextStyle(
                    fontWeight: FontWeight.w900,
                    fontSize: 24
                ),
              ),
            ],
          ),
        ),

        Container(
          height: 80,
          margin: const EdgeInsets.only(left: 10, bottom: 10),
          child: FutureBuilder<List<dynamic>>(
            future: futureList,
              builder: (context, snapshot) {
                if (snapshot.connectionState == ConnectionState.waiting) {
                  return Center(child: CircularProgressIndicator(
                    valueColor: new AlwaysStoppedAnimation<Color>(mainColor),
                  ),);
                } else if (snapshot.hasError) {
                  return Center(child: Text('${snapshot.error}'));
                } else if (!snapshot.hasData || snapshot.data!.isEmpty) {
                  return Center(child: Text('Aucune donnée correspondante!!'));
                }
                else {
                  return
                    ListView.builder(
                        scrollDirection: Axis.horizontal,
                        itemCount: snapshot.data?.length,
                        itemBuilder: (BuildContext context, index) {
                          final data = snapshot.data![index];
                          return GestureDetector(
                            onTap: () {
                              print("on m'a tapé");
                              Navigator.push(context, MaterialPageRoute(builder: (context) => CategorieDetails(categoryId: data.id),
                              ),);
                            },
                            child: Container(
                              width: 120,
                              margin: const EdgeInsets.only(top: 10, right: 10),
                              padding: const EdgeInsets.all(10),
                              decoration: BoxDecoration(
                                border: Border.all(
                                    color: Colors.grey.withOpacity(
                                        0.9), width: 2),
                                borderRadius: BorderRadius.circular(10),
                              ),
                              child: Column(
                                mainAxisAlignment: MainAxisAlignment.center,
                                children: [
                                  //Icon(currentCategory.icon, color: mainColor,),
                                  Text(
                                    data.name,
                                    style: const TextStyle(
                                      fontSize: 15,
                                      fontWeight: FontWeight.bold,
                                    ),
                                  )
                                ],
                              ),
                            ),
                          );
                        }
                    );
                }
              }
          ),
        )
      ],
    );
  }
}
