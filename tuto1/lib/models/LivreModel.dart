import 'dart:convert';

List<LivreModel> livreModelFromJson(String str) => List<LivreModel>.from(json.decode(str).map((x) => LivreModel.fromJson(x)));

String livreModelToJson(List<LivreModel> data) => json.encode(List<dynamic>.from(data.map((x) => x.toJson())));

class LivreModel {
  int? id;
  String? title;
  String? nomAuteur;
  String? localisation;
  dynamic nombreDisponible;
  String? dateParrution;
  String? decription;
  bool? recent;
  dynamic plusDemande;
  List<dynamic>? prets;

  LivreModel({
    this.id,
    this.title,
    this.nomAuteur,
    this.localisation,
    this.nombreDisponible,
    this.dateParrution,
    this.decription,
    this.recent,
    this.plusDemande,
    this.prets,
  });

  factory LivreModel.fromJson(Map<String, dynamic> json) => LivreModel(
    id: json["id"],
    title: json["title"],
    nomAuteur: json["nomAuteur"],
    localisation: json["localisation"],
    nombreDisponible: json["nombreDisponible"],
    dateParrution: json["dateParrution"],
    decription: json["decription"],
    recent: json["recent"],
    plusDemande: json["plusDemande"],
    prets: json["prets"] == null ? [] : List<dynamic>.from(json["prets"]!.map((x) => x)),
  );

  Map<String, dynamic> toJson() => {
    "id": id,
    "title": title,
    "nomAuteur": nomAuteur,
    "localisation": localisation,
    "nombreDisponible": nombreDisponible,
    "dateParrution": dateParrution,
    "decription": decription,
    "recent": recent,
    "plusDemande": plusDemande,
    "prets": prets == null ? [] : List<dynamic>.from(prets!.map((x) => x)),
  };
}
