library opinion;

import 'dart:convert';

class Opinion {
  String id;
  int hotelid;
  int custid;
  double note;
  int welcome;
  int atmosphere;
  int comfort;

  Opinion(this.id, this.hotelid, this.custid, this.note, this.welcome, this.atmosphere, this.comfort);

  factory Opinion.fromJsonMap(Map json) => new Opinion(json['id'], json['hotelid'], json['custid'], json['note'], json['welcome'], json['atmosphere'], json['comfort']);
  Map toJson() => {'id': id, 'hotelid': hotelid, 'custid': custid, 'note': note, 'welcome': welcome, 'atmosphere': atmosphere, 'comfort': comfort};
  String toJsonString() => JSON.encode(toJson());
}