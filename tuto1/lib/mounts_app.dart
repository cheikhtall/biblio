import 'package:flutter/material.dart';
import 'package:tuto1/_utils/colors.dart';
import 'package:tuto1/widgets/app_category_list.dart';
import 'package:tuto1/widgets/app_header.dart';
import 'package:tuto1/widgets/app_livre_recent_listview.dart';
import 'package:tuto1/widgets/app_search.dart';
import 'package:tuto1/widgets/drawer_widget.dart';
import 'package:tuto1/widgets/plus_demande_list_view.dart';

class MountsApp extends StatefulWidget {
  const MountsApp({Key? key}) : super(key: key);

  @override
  _MountsAppState createState() => _MountsAppState();
}

class _MountsAppState extends State<MountsApp> {
  void initState() {
    super.initState();
  }
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
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
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            AppHeader(),
            SizedBox(height: 20,),
            AppPlusDemandeListView(),
            SizedBox(height: 5,),
            AppLivreRecentListView(),
            SizedBox(height: 5,),
            AppCategoryList(),
          ],
        ),
      ),
    );
  }
}
