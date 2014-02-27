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

  Map toJson() => {
      'id': id, 'hotelid': hotelid, 'custid': custid, 'note': note, 'welcome': welcome, 'atmosphere': atmosphere, 'comfort': comfort
  };

  String toJsonString() => JSON.encode(toJson());
}

class Hotel {
  int id;
  String name;
  String city;
  String state;
  String countryCode;
  String addr1;
  String addr2;
  String zipCode;
  double latitude;
  double longitude;

  Hotel(this.id, this.name, this.city, this.state, this.countryCode, this.addr1, this.addr2, this.zipCode, this.latitude, this.longitude);

  factory Hotel.fromJsonMap(Map json) => new Hotel(json['id'], json['name'], json['city'], json['state'], json['countryCode'], json['addr1'], json['addr2'], json['zipCode'], json['latitude'], json['longitude']);

  Map toJson() => {
      'id': id, 'name': name, 'city': city, 'state': state, 'countryCode': countryCode, 'addr1': addr1, 'addr2': addr2, 'zipCode': zipCode, 'latitude':latitude, 'longitude':longitude
  };

  String toJsonString() => JSON.encode(toJson());
}