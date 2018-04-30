package es.usc.citius.hmb.games;

//import es.usc.citius.hmb.sdk.mapping.URIClassMapper;
//import es.usc.citius.hmb.sdk.plugin.annotations.MappingClassProvider;

import java.util.Map;
import java.util.HashMap;


public class DomainObjectMappingHelper {

    /*private static final Map<String, Class> uriToClass = new HashMap<>();
    private static final Map<Class, String> classToUri = new HashMap<>();
    static {

            uriToClass.put("http://citius.usc.es/hmb/citius.owl#Tag", es.usc.citius.hmb.games.Tag.class);
		    classToUri.put(es.usc.citius.hmb.games.Tag.class, "http://citius.usc.es/hmb/citius.owl#Tag");

            uriToClass.put("http://citius.usc.es/hmb/citius.owl#QuestionType", es.usc.citius.hmb.games.QuestionType.class);
		    classToUri.put(es.usc.citius.hmb.games.QuestionType.class, "http://citius.usc.es/hmb/citius.owl#QuestionType");

            uriToClass.put("http://citius.usc.es/hmb/citius.owl#AnswerType", es.usc.citius.hmb.games.AnswerType.class);
		    classToUri.put(es.usc.citius.hmb.games.AnswerType.class, "http://citius.usc.es/hmb/citius.owl#AnswerType");

            uriToClass.put("http://citius.usc.es/hmb/citius.owl#Question", es.usc.citius.hmb.games.Question.class);
		    classToUri.put(es.usc.citius.hmb.games.Question.class, "http://citius.usc.es/hmb/citius.owl#Question");

            uriToClass.put("http://citius.usc.es/hmb/citius.owl#VideoQuestionType", es.usc.citius.hmb.games.VideoQuestionType.class);
		    classToUri.put(es.usc.citius.hmb.games.VideoQuestionType.class, "http://citius.usc.es/hmb/citius.owl#VideoQuestionType");

            uriToClass.put("http://citius.usc.es/hmb/citius.owl#PictureQuestionType", es.usc.citius.hmb.games.PictureQuestionType.class);
		    classToUri.put(es.usc.citius.hmb.games.PictureQuestionType.class, "http://citius.usc.es/hmb/citius.owl#PictureQuestionType");

            uriToClass.put("http://citius.usc.es/hmb/citius.owl#Option", es.usc.citius.hmb.games.Option.class);
		    classToUri.put(es.usc.citius.hmb.games.Option.class, "http://citius.usc.es/hmb/citius.owl#Option");

            uriToClass.put("http://citius.usc.es/hmb/citius.owl#OptionQuestionType", es.usc.citius.hmb.games.OptionQuestionType.class);
		    classToUri.put(es.usc.citius.hmb.games.OptionQuestionType.class, "http://citius.usc.es/hmb/citius.owl#OptionQuestionType");

            uriToClass.put("http://citius.usc.es/hmb/citius.owl#ChooseOneOptionQuestion", es.usc.citius.hmb.games.ChooseOneOptionQuestion.class);
		    classToUri.put(es.usc.citius.hmb.games.ChooseOneOptionQuestion.class, "http://citius.usc.es/hmb/citius.owl#ChooseOneOptionQuestion");

            uriToClass.put("http://citius.usc.es/hmb/citius.owl#ChooseVariousOptionsQuestion", es.usc.citius.hmb.games.ChooseVariousOptionsQuestion.class);
		    classToUri.put(es.usc.citius.hmb.games.ChooseVariousOptionsQuestion.class, "http://citius.usc.es/hmb/citius.owl#ChooseVariousOptionsQuestion");

            uriToClass.put("http://citius.usc.es/hmb/citius.owl#TrueFalseQuestionType", es.usc.citius.hmb.games.TrueFalseQuestionType.class);
		    classToUri.put(es.usc.citius.hmb.games.TrueFalseQuestionType.class, "http://citius.usc.es/hmb/citius.owl#TrueFalseQuestionType");

            uriToClass.put("http://citius.usc.es/hmb/citius.owl#TextQuestionType", es.usc.citius.hmb.games.TextQuestionType.class);
		    classToUri.put(es.usc.citius.hmb.games.TextQuestionType.class, "http://citius.usc.es/hmb/citius.owl#TextQuestionType");

            uriToClass.put("http://citius.usc.es/hmb/citius.owl#TextSolution", es.usc.citius.hmb.games.TextSolution.class);
		    classToUri.put(es.usc.citius.hmb.games.TextSolution.class, "http://citius.usc.es/hmb/citius.owl#TextSolution");

            uriToClass.put("http://citius.usc.es/hmb/citius.owl#InsertOneTextQuestion", es.usc.citius.hmb.games.InsertOneTextQuestion.class);
		    classToUri.put(es.usc.citius.hmb.games.InsertOneTextQuestion.class, "http://citius.usc.es/hmb/citius.owl#InsertOneTextQuestion");

            uriToClass.put("http://citius.usc.es/hmb/citius.owl#InsertVariousTextsQuestion", es.usc.citius.hmb.games.InsertVariousTextsQuestion.class);
		    classToUri.put(es.usc.citius.hmb.games.InsertVariousTextsQuestion.class, "http://citius.usc.es/hmb/citius.owl#InsertVariousTextsQuestion");

            uriToClass.put("http://citius.usc.es/hmb/citius.owl#Questionnaire", es.usc.citius.hmb.games.Questionnaire.class);
		    classToUri.put(es.usc.citius.hmb.games.Questionnaire.class, "http://citius.usc.es/hmb/citius.owl#Questionnaire");

            uriToClass.put("http://citius.usc.es/hmb/citius.owl#QuestionWithRating", es.usc.citius.hmb.games.QuestionWithRating.class);
		    classToUri.put(es.usc.citius.hmb.games.QuestionWithRating.class, "http://citius.usc.es/hmb/citius.owl#QuestionWithRating");

            uriToClass.put("http://citius.usc.es/hmb/citius.owl#Answer", es.usc.citius.hmb.games.Answer.class);
		    classToUri.put(es.usc.citius.hmb.games.Answer.class, "http://citius.usc.es/hmb/citius.owl#Answer");

            uriToClass.put("http://citius.usc.es/hmb/citius.owl#QuestionnaireAnswer", es.usc.citius.hmb.games.QuestionnaireAnswer.class);
		    classToUri.put(es.usc.citius.hmb.games.QuestionnaireAnswer.class, "http://citius.usc.es/hmb/citius.owl#QuestionnaireAnswer");

            uriToClass.put("http://citius.usc.es/hmb/citius.owl#ChooseOneOptionQuestionAnswer", es.usc.citius.hmb.games.ChooseOneOptionQuestionAnswer.class);
		    classToUri.put(es.usc.citius.hmb.games.ChooseOneOptionQuestionAnswer.class, "http://citius.usc.es/hmb/citius.owl#ChooseOneOptionQuestionAnswer");

            uriToClass.put("http://citius.usc.es/hmb/citius.owl#ChooseVariousOptionsQuestionAnswer", es.usc.citius.hmb.games.ChooseVariousOptionsQuestionAnswer.class);
		    classToUri.put(es.usc.citius.hmb.games.ChooseVariousOptionsQuestionAnswer.class, "http://citius.usc.es/hmb/citius.owl#ChooseVariousOptionsQuestionAnswer");

            uriToClass.put("http://citius.usc.es/hmb/citius.owl#InsertOneTextQuestionAnswer", es.usc.citius.hmb.games.InsertOneTextQuestionAnswer.class);
		    classToUri.put(es.usc.citius.hmb.games.InsertOneTextQuestionAnswer.class, "http://citius.usc.es/hmb/citius.owl#InsertOneTextQuestionAnswer");

            uriToClass.put("http://citius.usc.es/hmb/citius.owl#InsertVariousTextsQuestionAnswer", es.usc.citius.hmb.games.InsertVariousTextsQuestionAnswer.class);
		    classToUri.put(es.usc.citius.hmb.games.InsertVariousTextsQuestionAnswer.class, "http://citius.usc.es/hmb/citius.owl#InsertVariousTextsQuestionAnswer");

            uriToClass.put("http://citius.usc.es/hmb/citius.owl#TrueFalseQuestionAnswer", es.usc.citius.hmb.games.TrueFalseQuestionAnswer.class);
		    classToUri.put(es.usc.citius.hmb.games.TrueFalseQuestionAnswer.class, "http://citius.usc.es/hmb/citius.owl#TrueFalseQuestionAnswer");

            uriToClass.put("http://citius.usc.es/hmb/citius.owl#Evaluation", es.usc.citius.hmb.games.Evaluation.class);
		    classToUri.put(es.usc.citius.hmb.games.Evaluation.class, "http://citius.usc.es/hmb/citius.owl#Evaluation");

            uriToClass.put("http://citius.usc.es/hmb/citius.owl#QuestionnaireEvaluation", es.usc.citius.hmb.games.QuestionnaireEvaluation.class);
		    classToUri.put(es.usc.citius.hmb.games.QuestionnaireEvaluation.class, "http://citius.usc.es/hmb/citius.owl#QuestionnaireEvaluation");

    }

    public Class getClassForUri(String uri) {
        return uriToClass.get(uri);
    }

    public String getUriForClass(Class classs) {
        return classToUri.get(classs);
    }*/
}
