import 'dart:convert';

List<AdherantModel> adherantModelFromJson(String str) => List<AdherantModel>.from(json.decode(str).map((x) => AdherantModel.fromJson(x)));

String adherantModelToJson(List<AdherantModel> data) => json.encode(List<dynamic>.from(data.map((x) => x.toJson())));

class AdherantModel {
  int? id;
  String? email;
  String? firstname;
  String? lastName;
  String? phone;
  String? address;
  String? birth;

  AdherantModel({
    this.id,
    this.email,
    this.firstname,
    this.lastName,
    this.phone,
    this.address,
    this.birth,
  });

  factory AdherantModel.fromJson(Map<String, dynamic> json) => AdherantModel(
    id: json["id"],
    email: json["email"],
    firstname: json["firstname"],
    lastName: json["lastName"],
    phone: json["phone"],
    address: json["address"],
    birth: json["birth"],
  );

  Map<String, dynamic> toJson() => {
    "id": id,
    "email": email,
    "firstname": firstname,
    "lastName": lastName,
    "phone": phone,
    "address": address,
    "birth": birth,
  };
}
