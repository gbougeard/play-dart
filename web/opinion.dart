import 'package:angular/angular.dart';
import 'package:perf_api/perf_api.dart';
import 'package:di/di.dart';
import 'package:logging/logging.dart';

import 'package:play_dart/domain.dart';
import 'package:play_dart/component/opinion/opinion_component.dart';
import 'package:play_dart/service/opinion_service.dart';


// Temporary, please follow https://github.com/angular/angular.dart/issues/476
@MirrorsUsed(targets: const['opinion', 'opinion_service'], override: '*')
import 'dart:mirrors';

/* Use the NgController annotation to indicate that this class is an
 * Angular Controller. The compiler will instantiate the controller if
 * it finds it in the DOM.
 *
 * The selector field defines the CSS selector that will trigger the
 * controller. It can be any valid CSS selector which does not cross
 * element boundaries.
 *
 * The publishAs field specifies that the controller instance should be
 * assigned to the current scope under the name specified.
 *
 * The controller's public fields are available for data binding from the view.
 * Similarly, the controller's public methods can be invoked from the view.
 */
@NgController(selector: '[opinions]', publishAs: 'ctrl')
class OpinionController {
  final Logger log = new Logger('OpinionController');

  Http _http;
  OpinionService _opinionService;

  OpinionService get opinionService => _opinionService;

  // Determine the initial load state of the app
  bool recipesLoaded = false;
  bool categoriesLoaded = false;

  List<Opinion> _opinions;

  List<Opinion >get opinions => _opinions;

  OpinionController(Http this._http, OpinionService this._opinionService) {
    log.info("Construct");
    _loadData();
  }

  Opinion selectedOpinion;

  void selectOpinion(Opinion opinion) {
    selectedOpinion = opinion;
  }

  void _loadData() {
    log.fine("loadData");
    _opinionService.getAllOpinions(12, 10002).then((List<Opinion> allOpinions) {
      _opinions = allOpinions;
      recipesLoaded = true;
    }) ;
//    return [
//        new Opinion("test1", 2,143,1.0),
//        new Opinion("test2", 2,143,2.0),
//        new Opinion("test3", 2,143,3.0),
//        new Opinion("test4", 2,143,4.0),
//        new Opinion("test5", 2,143,5.0),
//        new Opinion("test6", 2,143,6.0),
//        new Opinion("test7", 2,143,4.0),
//    ];
  }
}

class MyAppModule extends Module {
  MyAppModule() {
    type(OpinionController);
    type(OpinionComponent);
    type(OpinionService);
//    type(Profiler, implementedBy: Profiler);
    // comment out to enable profiling
  }
}

main() {
  Logger.root.level = Level.ALL;
  Logger.root.onRecord.listen((LogRecord rec) {
    print('${rec.level.name}: ${rec.time}: ${rec.message}');
  });

  ngBootstrap(module: new MyAppModule());
}
