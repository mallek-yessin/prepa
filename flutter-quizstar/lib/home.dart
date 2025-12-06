import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:http/http.dart' as http;
import 'package:quizstar/quizpage.dart';

class homepage extends StatefulWidget {
  final List chapitres;

  const homepage({super.key, required this.chapitres});

  @override
  _homepageState createState() => _homepageState(chapitres);
}

class getchapitre extends StatelessWidget {
  Future<List> fetchQuestions() async {
    final response =
        await http.get(Uri.parse('http://192.168.1.46:8080/api/chapters'));

    if (response.statusCode == 200) {
      final data = json.decode(response.body);
      return data;
    } else {
      throw Exception('Failed to load questions');
    }
  }

  @override
  Widget build(BuildContext context) {
    return FutureBuilder<List>(
      future: fetchQuestions(),
      builder: (context, snapshot) {
        if (snapshot.connectionState == ConnectionState.waiting) {
          return Scaffold(body: Center(child: Text("Loading...")));
        } else if (snapshot.hasError) {
          return Scaffold(
              body: Center(child: Text("Error: ${snapshot.error}")));
        } else if (!snapshot.hasData) {
          return Scaffold(body: Center(child: Text("No data")));
        } else {
          return homepage(chapitres: snapshot.data!);
        }
      },
    );
  }
}

class _homepageState extends State<homepage> {
  List<String> images = [
    "images/py.png",
    "images/java.png",
    "images/js.png",
    "images/cpp.png",
    "images/linux.png",
  ];

  List<String> des = [
    "Python is one of the most popular and fastest programming language since half a decade.\nIf You think you have learnt it.. \nJust test yourself !!",
    "Java has always been one of the best choices for Enterprise World. If you think you have learn the Language...\nJust Test Yourself !!",
    "Javascript is one of the most Popular programming language supporting the Web.\nIt has a wide range of Libraries making it Very Powerful !",
    "C++, being a statically typed programming language is very powerful and Fast.\nit's DMA feature makes it more useful. !",
    "Linux is a OPEN SOURCE Operating System which powers many Servers and Workstation.\nIt is also a top Priority in Developement Work !",
  ];

  final List chapitres;

  _homepageState(this.chapitres);

  print(chapitres) {
    // TODO: implement print
    throw UnimplementedError();
  }

  Widget customcard(String langname, String image, String des) {
    return Padding(
      padding: EdgeInsets.symmetric(
        vertical: 20.0,
        horizontal: 30.0,
      ),
      child: InkWell(
        onTap: () {
          Navigator.of(context).pushReplacement(MaterialPageRoute(
            // in changelog 1 we will pass the langname name to ther other widget class
            // this name will be used to open a particular JSON file
            // for a particular language
            builder: (context) => getjson(langname),
          ));
        },
        child: Material(
          color: Colors.indigoAccent,
          elevation: 10.0,
          borderRadius: BorderRadius.circular(25.0),
          child: Container(
            child: Column(
              children: <Widget>[
                Padding(
                  padding: EdgeInsets.symmetric(
                    vertical: 10.0,
                  ),
                  child: Material(
                    elevation: 5.0,
                    borderRadius: BorderRadius.circular(100.0),
                    child: Container(
                      // changing from 200 to 150 as to look better
                      height: 150.0,
                      width: 150.0,
                      child: ClipOval(
                        child: Image(
                          fit: BoxFit.cover,
                          image: AssetImage(
                            image,
                          ),
                        ),
                      ),
                    ),
                  ),
                ),
                Center(
                  child: Text(
                    langname,
                    style: TextStyle(
                      fontSize: 20.0,
                      color: Colors.white,
                      fontFamily: "Quando",
                      fontWeight: FontWeight.w700,
                    ),
                  ),
                ),
                Container(
                  padding: EdgeInsets.all(20.0),
                  child: Text(
                    des,
                    style: TextStyle(
                        fontSize: 18.0,
                        color: Colors.white,
                        fontFamily: "Alike"),
                    maxLines: 5,
                    textAlign: TextAlign.justify,
                  ),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    SystemChrome.setPreferredOrientations(
        [DeviceOrientation.portraitDown, DeviceOrientation.portraitUp]);
    return Scaffold(
      appBar: AppBar(
        title: Text(
          "Quizstar",
          style: TextStyle(
            fontFamily: "Quando",
          ),
        ),
      ),
      body: ListView.builder(
        itemCount: chapitres.length,
        itemBuilder: (context, index) {
          return customcard(
            chapitres[index],
            images[index],
            des[index],
          );
        },
      ),
    );
  }
}
