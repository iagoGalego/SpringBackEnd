package es.usc.citius.hmb.games;

public class SimpleFillInTheBlanksQuestionAnswer extends Answer {
	private static final long serialVersionUID = 1L;
	public static final String RDFS_CLASS = "http://citius.usc.es/hmb/citius.owl#SimpleFillInTheBlanksQuestionAnswer";
	private es.usc.citius.hmb.model.StringType solution;

	public SimpleFillInTheBlanksQuestionAnswer() {
		super();
	}

	public SimpleFillInTheBlanksQuestionAnswer(String uri) {
		super(uri, false);
	}

	public SimpleFillInTheBlanksQuestionAnswer(String uri, boolean b) {
		super(uri, false);
	}

	public void setSolution(es.usc.citius.hmb.model.StringType value) {
		this.solution = value;
	}

	public es.usc.citius.hmb.model.StringType getSolution() {
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
