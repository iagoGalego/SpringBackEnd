package es.usc.citius.hmb.games;

public class TrueFalseQuestionType extends AnswerType {
	private static final long serialVersionUID = 1L;
	public static final String RDFS_CLASS = "http://citius.usc.es/hmb/citius.owl#TrueFalseQuestionType";
	private es.usc.citius.hmb.model.BooleanType solution;
	private es.usc.citius.hmb.model.IntegerType score;

	public TrueFalseQuestionType() {
		super();
	}

	public TrueFalseQuestionType(String uri) {
		super(uri, false);
	}

	public TrueFalseQuestionType(String uri, boolean b) {
		super(uri, false);
	}

	public void setSolution(es.usc.citius.hmb.model.BooleanType value) {
		this.solution = value;
	}

	public es.usc.citius.hmb.model.BooleanType getSolution() {
		return this.solution;
	}

	public void setScore(es.usc.citius.hmb.model.IntegerType value) {
		this.score = value;
	}

	public es.usc.citius.hmb.model.IntegerType getScore() {
		return this.score;
	}

    @Override
    public void genURI() {
        super.genURI();
        
        if (this.solution != null) {
            this.solution.genURI();
        }

        if (this.score != null) {
            this.score.genURI();
        }

    }
}
