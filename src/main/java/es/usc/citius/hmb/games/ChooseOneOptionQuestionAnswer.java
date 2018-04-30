package es.usc.citius.hmb.games;

public class ChooseOneOptionQuestionAnswer extends Answer {
	private static final long serialVersionUID = 1L;
	public static final String RDFS_CLASS = "http://citius.usc.es/hmb/citius.owl#ChooseOneOptionQuestionAnswer";
	private Option solution;

	public ChooseOneOptionQuestionAnswer() {
		super();
	}

	public ChooseOneOptionQuestionAnswer(String uri) {
		super(uri, false);
	}

	public ChooseOneOptionQuestionAnswer(String uri, boolean b) {
		super(uri, false);
	}

	public void setSolution(Option value) {
		this.solution = value;
	}

	public Option getSolution() {
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
