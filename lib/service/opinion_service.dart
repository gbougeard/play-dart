library opinion_service;

import 'dart:async';
import 'package:angular/angular.dart';
import 'package:logging/logging.dart';

import '../domain.dart';

class OpinionService {
  final Logger log = new Logger('OpinionService');

  String _opinionUrl = 'http://luc.dev.it.int:9000/opinions';
  Http _http;

  OpinionService(Http this._http) {
  }

  Future<List<Opinion>> getAllOpinions(int custid, int hotelid) {
    log.fine("getAllOpinions $custid $hotelid ");

    return _http.get('$_opinionUrl/$custid/$hotelid').then((HttpResponse response) {
      List<Opinion> opinions = new List<Opinion>();
      for(Map map in response.data) {
        opinions.add(new Opinion.fromJsonMap(map));
      }
      return opinions;

    })
    .catchError((e) {
      print(e);
      log.severe(e);
    });
  }

}