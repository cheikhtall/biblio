import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart';

import 'auth_repository.dart';

class AuthProvider with ChangeNotifier {
  final AuthRepository _authRepository;

  AuthProvider({required AuthRepository authRepository})
      : _authRepository = authRepository;

  bool _isAuthenticated = false;

  bool get isAuthenticated => _isAuthenticated;

  Future<bool> login(String username, String password) async {
    final token = await _authRepository.authenticate(username, password);
    _isAuthenticated = true;
    notifyListeners();
    return true;
  }

  Future<void> logout() async {
    await _authRepository.logout();
    _isAuthenticated = false;
    notifyListeners();
  }

  Future<bool> checkAuthentication() async {
    final isAuthenticated = await _authRepository.isAuthenticated();
    _isAuthenticated = isAuthenticated;
    notifyListeners();
    return isAuthenticated;
  }
}
