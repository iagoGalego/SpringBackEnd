package es.usc.citius.hmb.games;

public class TrueFalseQuestionAnswer extends Answer {
	private static final long serialVersionUID = 1L;
	public static final String RDFS_CLASS = "http://citius.usc.es/hmb/citius.owl#TrueFalseQuestionAnswer";
	private es.usc.citius.hmb.model.BooleanType solution;

	public TrueFalseQuestionAnswer() {
		super();
	}

	public TrueFalseQuestionAnswer(String uri) {
		super(uri, false);
	}

	public TrueFalseQuestionAnswer(String uri, boolean b) {
		super(uri, false);
	}

	public void setSolution(es.usc.citius.hmb.model.BooleanType value) {
		this.solution = value;
	}

	public es.usc.citius.hmb.model.BooleanType getSolution() {
		return this.solution;
	}

    @Override
    public void genURI() {
        super.genURI();
        
        if (this.solution != null) {
            this.solution.genURI();
        }

    }
}
