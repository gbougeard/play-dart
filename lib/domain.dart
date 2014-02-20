library opinion;

import 'dart:convert';

class Opinion {
  String id;
  int hotelid;
  int custid;
  double note;

  Opinion(this.id, this.hotelid, this.custid, this.note);

  factory Opinion.fromJsonMap(Map json) => new Opinion(json['id'], json['hotelid'], json['custid'], json['note']);
  Map toJson() => {'id': id, 'hotelid': hotelid, 'custid': custid, 'note': note};
  String toJsonString() => JSON.encode(toJson());
}