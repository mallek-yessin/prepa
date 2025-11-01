import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

class QuestionPage extends StatefulWidget {
  const QuestionPage({super.key});

  @override
  State<QuestionPage> createState() => _QuestionPageState();
}

class _QuestionPageState extends State<QuestionPage> {
  Future<Map<String, dynamic>> fetchQuestion(int id) async {
    final url = Uri.parse('http://192.168.1.46:8080/api/questions/$id');
    final response = await http.get(url);

    if (response.statusCode == 200) {
      return jsonDecode(response.body);
    } else {
      throw Exception('Erreur serveur: ${response.statusCode}');
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(

      body: FutureBuilder<Map<String, dynamic>>(
        future: fetchQuestion(1),
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.waiting) {
            return const Center(child: CircularProgressIndicator());
          } else if (snapshot.hasError) {
            return Center(child: Text('Erreur: ${snapshot.error}'));
          } else if (!snapshot.hasData) {
            return const Center(child: Text('Aucune donnée.'));
          }

          final data = snapshot.data!;
          final questions = Map<String, dynamic>.from(data['question']['questions']);
          final responses = Map<String, dynamic>.from(data['reponse']['responses']);
          final correctAnswers = Map<String, dynamic>.from(data['correctAnswer']['correctAnswers']);
          print("data");
          print(data);


          return ListView.builder(
            padding: const EdgeInsets.all(16),
            itemCount: questions.length,
            itemBuilder: (context, index) {
              final key = (index + 1).toString();
              final questionText = questions[key] ?? '';
              final answers = Map<String, dynamic>.from(responses[key] ?? {});
              final correct = correctAnswers[key] ?? '';

              return Card(
                margin: const EdgeInsets.only(bottom: 16),
                elevation: 3,
                child: Padding(
                  padding: const EdgeInsets.all(12),
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text(
                        'Q${key}. $questionText',
                        style: const TextStyle(fontSize: 16, fontWeight: FontWeight.bold),
                      ),
                      const SizedBox(height: 8),
                      ...answers.entries.map((e) => Text('${e.key}) ${e.value}')),
                      const SizedBox(height: 8),

                      Text(
                        'Bonne réponse : $correct',
                        style: const TextStyle(color: Colors.green, fontWeight: FontWeight.w600),
                      ),
                    ],
                  ),
                ),
              );
            },
          );
        },
      ),
    );
  }
}
