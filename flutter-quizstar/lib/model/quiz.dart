class quiz{
  late String question;
  late String correctAnswer;
  late String answer1;
  late String answer2;
  late String answer3;


  quiz({required this.question,required this.correctAnswer,required this.answer1,required this.answer2,required this.answer3});
  factory quiz.fromJson(Map<String, dynamic> json) {
    return quiz(
      question: json['question'],
      correctAnswer: json['correctAnswer'],
      answer1: json['answer1'],
      answer2: json['answer2'],
      answer3: json['answer3'],
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'question': question,
      'correctAnswer': correctAnswer,
      'answer1': answer1,
      'answer2': answer2,
      'answer3': answer3,
    };

  }
  String get Question => question;

  String get correctanswer => correctAnswer;
  String get Answer1 => answer1;
  String get Answer2 => answer2;
  String get Answer3 => answer3;
}