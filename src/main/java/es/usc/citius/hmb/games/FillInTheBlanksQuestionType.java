package es.usc.citius.hmb.games;

public class FillInTheBlanksQuestionType extends AnswerType {
	private static final long serialVersionUID = 1L;
	public static final String RDFS_CLASS = "http://citius.usc.es/hmb/citius.owl#FillInTheBlanksQuestionType";
	private es.usc.citius.hmb.model.StringType sentence;

	public FillInTheBlanksQuestionType() {
		super();
	}

	public FillInTheBlanksQuestionType(String uri) {
		super(uri, false);
	}

	public FillInTheBlanksQuestionType(String uri, boolean b) {
		super(uri, false);
	}

	public void setSentence(es.usc.citius.hmb.model.StringType value) {
		this.sentence = value;
	}

	public es.usc.citius.hmb.model.StringType getSentence() {
		return this.sentence;
	}

    @Override
    public void genURI() {
        super.genURI();
        
        if (this.sentence != null) {
            this.sentence.genURI();
        }

    }
}
