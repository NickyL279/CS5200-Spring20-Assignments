const mongoose = require('mongoose')

const quizwidgetSchema = require('./quiz-widget.schema.server')
module.exports = mongoose.model('Quiz_WidgetModel', quizwidgetSchema)