require('./db')();
const dao = require('./daos/university.dao.server');
const assert = require('assert');

dao.truncateDatabase().then(() => dao.populateDatabase())
    .then(() => testStudentsInitialCount())
    .then(() => testQuestionsInitialCount())
    .then(() => testAnswersInitialCount())
    .then(() => testDeleteAnswer())
    .then(() => testDeleteQuestion())
    .then(() => testDeleteStudent());

const testStudentsInitialCount = () => {
    return dao.findAllStudents().then(students => assert.equal(students.length, 2))
};

const testQuestionsInitialCount = () => {
    return dao.findAllQuestions().then(questions => assert.equal(questions.length, 4))
};

const testAnswersInitialCount = () => {
    return dao.findAllAnswers().then(answers => assert.equal(answers.length, 8))
};

const testDeleteAnswer = () => {
	return dao.deleteAnswer(890).then(() => { dao.findAllAnswers().then(answers => assert.equal(answers.length, 7));
		dao.findAnswersByStudent(234).then(bobAnswers => assert.equal(bobAnswers.length, 3));
	});
};

const testDeleteQuestion = () => {
	return dao.deleteQuestion(321).then(() => dao.findAllQuestions().then(questions => assert.equal(questions.length, 3)))
};

const testDeleteStudent = () => {
	return dao.deleteStudent(234).then(() => dao.findAllStudents().then(students => assert.equal(students.length, 1)))
};



