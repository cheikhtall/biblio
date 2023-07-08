import 'dart:convert';

import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:http/http.dart' as http;
import 'package:tuto1/models/LivreModel.dart';
import 'package:tuto1/models/adherant_model.dart';
import '../models/category_model.dart';
import 'package:jwt_decoder/jwt_decoder.dart';

class AuthRepository {
  static const String _baseUrl = "http://10.0.2.2:8081/api";

  final _storage = FlutterSecureStorage();
  String decodedEmail="";

  Future<String?> authenticate(String username, String password) async {
    final response = await http.post(
      Uri.parse("$_baseUrl/login"),
      headers: <String, String>{
        'Content-Type': 'application/json',
      },
      body: jsonEncode(<String, String>{
        'username': username,
        'password': password,
      }),
    );
    print(response.body);
    if (response.statusCode == 200) {
      final token = jsonDecode(response.body)['access_token'];
      await _storage.write(key: 'jwt_token', value: token);
      decodeToken(token);
      return token;
    } else {
      return(null);
    }
  }
  void decodeToken(String token) {
    Map<String, dynamic> decodedToken = JwtDecoder.decode(token);

    // Accédez aux informations du token décodé
    String email = decodedToken['email'];
    String name = decodedToken['name'];
  }

  Future<void> logout() async {
    await _storage.delete(key: 'jwt_token');
  }

  Future<bool> isAuthenticated() async {
    final token = await _storage.read(key: 'jwt_token');
    return token != null;
  }

  Future<List<dynamic>> getCategories() async {
    final response = await http.get(Uri.parse("$_baseUrl/categories"));
    if (response.statusCode == 200) {
      print(response.body);
      // La requête s'est bien passée, on parse la réponse
      final parsed = jsonDecode(response.body).cast<Map<String, dynamic>>();
      var parsed2 = parsed.map((json) => CategoryModel.fromJson(json)).toList();
      print("******** LIVRES*************");
      // On retourne la liste des éléments
      return parsed.map((json) => CategoryModel.fromJson(json)).toList();
    } else {
      // La requête a échoué, on lance une exception
      throw Exception('Failed to load list from API');
    }
  }

  Future<List<dynamic>> getLivresByTitle(String title) async {
    final response = await http.get(Uri.parse("$_baseUrl/livres/$title"));
    if (response.statusCode == 200) {
      // La requête s'est bien passée, on parse la réponse
      final parsed = jsonDecode(response.body).cast<Map<String, dynamic>>();
      // On retourne la liste des éléments
      return parsed.map((json) => LivreModel.fromJson(json)).toList();
    } else {
      // La requête a échoué, on lance une exception
      throw Exception('Aucun element trouvé');
    }
  }

  Future<List<dynamic>> getLivresByPlusDemande(bool plusDemandes) async {
    final response = await http.get(Uri.parse("$_baseUrl/livresPlusDemandes/$plusDemandes"));
    if (response.statusCode == 200) {
      // La requête s'est bien passée, on parse la réponse
      final parsed = jsonDecode(response.body).cast<Map<String, dynamic>>();
      // On retourne la liste des éléments
      return parsed.map((json) => LivreModel.fromJson(json)).toList();
    } else {
      // La requête a échoué, on lance une exception
      throw Exception('Aucun element trouvé');
    }
  }
  Future<List<dynamic>> getLivresByRecent(bool recent) async {
    final response = await http.get(Uri.parse("$_baseUrl/livresRecents/$recent"));
    if (response.statusCode == 200) {
      // La requête s'est bien passée, on parse la réponse
      final parsed = jsonDecode(response.body).cast<Map<String, dynamic>>();
      // On retourne la liste des éléments
      return parsed.map((json) => LivreModel.fromJson(json)).toList();
    } else {
      // La requête a échoué, on lance une exception
      throw Exception('Aucun element trouvé');
    }
  }
  Future<LivreModel>getLivreById(int id) async {
    final response = await http.get(Uri.parse("$_baseUrl/livre/$id"));
    if(response.statusCode == 200){
      return LivreModel.fromJson(json.decode(response.body));
    }
    else {
      throw Exception('Failed to load element');
    }
  }

  Future<AdherantModel> getAdherantByEmail(String email) async {
    final response = await http.get(Uri.parse("$_baseUrl/adherantByEmail/$email"));
    if(response.statusCode == 200){
      return AdherantModel.fromJson(json.decode(response.body));
    }
    else {
      throw Exception('Failed to load element');
    }
  }

  Future<void> updateAdherant(int id, AdherantModel adherantModel) async {
    final response = await http.put(Uri.parse("$_baseUrl/adherant/$id"),
        body: jsonEncode(adherantModel.toJson()), // Utilisez jsonEncode pour convertir en JSON
        headers: {'Content-Type': 'application/json'},
    );
    if (response.statusCode == 200) {
      print('Élément mis à jour avec succès');
    } else {
      print('Échec de la mise à jour de l\'élément. Code d\'erreur : ${response.statusCode}');
    }
  }

  Future<CategoryModel>getCategorieById(int id) async {
    final response = await http.get(Uri.parse("$_baseUrl/categorie/$id"));
    if(response.statusCode == 200){
      var parsed2 = CategoryModel.fromJson(json.decode(response.body));
      print("******** LIVRES*************");

        //print(parsed2.livres);

      print(response.body);
      return CategoryModel.fromJson(json.decode(response.body));
    }
    else {
      throw Exception('Failed to load element');
    }
  }
}

