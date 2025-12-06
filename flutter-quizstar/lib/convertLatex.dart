import 'package:flutter/material.dart';
import 'package:flutter_math_fork/ast.dart';
import 'package:flutter_math_fork/flutter_math.dart';
import 'package:flutter_math_fork/tex.dart';

class LatexPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    String x = r'\frac{1}{x^2} + \sqrt{3}';
    return Scaffold(
        appBar: AppBar(title: Text("LaTeX Viewer")),
        body: Math.tex(
          x,
          textStyle: TextStyle(fontSize: 22),
        ));
  }
}
