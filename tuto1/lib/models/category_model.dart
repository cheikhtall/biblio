import 'package:tuto1/auth/auth_repository.dart';
import 'package:tuto1/models/LivreModel.dart';

// To parse this JSON data, do
//
//     final categoryModel = categoryModelFromJson(jsonString);

import 'dart:convert';

List<CategoryModel> categoryModelFromJson(String str) => List<CategoryModel>.from(json.decode(str).map((x) => CategoryModel.fromJson(x)));

String categoryModelToJson(List<CategoryModel> data) => json.encode(List<dynamic>.from(data.map((x) => x.toJson())));

class CategoryModel {
  int? id;
  String? name;
  String? description;
  List<LivreModel>? livres;

  CategoryModel({
    this.id,
    this.name,
    this.description,
    this.livres,
  });

  factory CategoryModel.fromJson(Map<String, dynamic> json) => CategoryModel(
    id: json["id"],
    name: json["name"],
    description: json["description"],
    livres: json["livres"] == null ? [] : List<LivreModel>.from(json["livres"]!.map((x) => LivreModel.fromJson(x))),
  );

  Map<String, dynamic> toJson() => {
    "id": id,
    "name": name,
    "description": description,
    "livres": livres == null ? [] : List<dynamic>.from(livres!.map((x) => x.toJson())),
  };
}
