library main_test;

import 'package:unittest/unittest.dart';
import 'package:di/di.dart';
import 'package:angular/angular.dart';
import 'package:angular/mock/module.dart';
//import 'package:angular_dart_demo/recipe_book.dart';
//import 'package:play_dart/opinion/opinion_component.dart';

import '../../lib/domain.dart';
import '../../lib/component/opinion/opinion_component.dart';
import '../main.dart';

main() {
  setUp(() {
    setUpInjector();
    module((Module m) => m.install(new MyAppModule()));
  });
  tearDown(tearDownInjector);

//  group('recipe-book', () {
//    test('should load recipes', inject((RecipeBookController recipesController) {
//      expect(recipesController.recipes, isNot(isEmpty));
//    }));
//
//    test('should select recipe', inject((RecipeBookController recipesController) {
//      var recipe = recipesController.recipes[0];
//      recipesController.selectRecipe(recipe);
//      expect(recipesController.selectedRecipe, same(recipe));
//    }));
//  });

  group('opinion component', () {
    test('should show the right number of stars', inject((OpinionComponent opinionCmp) {
      Opinion opinion = new Opinion("test", 2, 143, 4.5);
      opinionCmp.maxOpinion = '5';
      expect(opinionCmp.stars, equals([1, 2, 3, 4, 5]));
    }));

//    test('should handle click', inject((OpinionComponent opinion) {
//      opinion.maxOpinion = '5';
//      opinion.handleClick(3);
//      expect(opinion.opinion, equals(3));
//
//      opinion.handleClick(1);
//      expect(opinion.opinion, equals(1));
//
//      opinion.handleClick(1);
//      expect(opinion.opinion, equals(0));
//
//      opinion.handleClick(1);
//      expect(opinion.opinion, equals(1));
//    }));
  });
}