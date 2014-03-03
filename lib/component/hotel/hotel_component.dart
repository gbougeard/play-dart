library hotel_component;

import 'dart:html';
import 'package:angular/angular.dart';
import 'package:logging/logging.dart';

import '../../domain.dart';

/* Use the @NgComponent annotation to indicate that this class is an
 * Angular component.
 *
 * The selector field defines the CSS selector that will trigger the
 * component. Typically, the CSS selector is an element name.
 *
 * The templateUrl field tells the component which HTML template to use
 * for its view.
 *
 * The cssUrl field tells the component which CSS file to use.
 *
 * The publishAs field specifies that the component instance should be
 * assigned to the current scope under the name specified.
 *
 * The class field and setter annotated with @NgTwoWay and @NgAttr,
 * respectively, identify the attributes that can be set on
 * the component. Users of this component will specify these attributes
 * in the HTML tag that is used to create the component. For example:
 *
 * <hotel max-hotel="5" hotel="mycontrol.hotel">
 *
 * The component's public fields are available for data binding from the
 * component's view. Similarly, the component's public methods can be
 * invoked from the component's view.
 */
@NgComponent(
    selector: 'hotel',
    templateUrl: 'packages/play_dart/component/hotel/hotel_component.html',
    cssUrl: 'packages/play_dart/component/hotel/hotel_component.css',
    publishAs: 'cmp'
)
class HotelComponent {
  final Logger log = new Logger('HotelComponent');


  @NgTwoWay('hotel')
  Hotel hotel;


//  void handleClick(int star) {
//    hotel = (star == 1 && hotel.note == 1) ? 0 : star;
//  }
}

