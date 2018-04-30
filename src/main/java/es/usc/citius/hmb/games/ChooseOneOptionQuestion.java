package es.usc.citius.hmb.games;

public class ChooseOneOptionQuestion extends OptionQuestionType {
	private static final long serialVersionUID = 1L;
	public static final String RDFS_CLASS = "http://citius.usc.es/hmb/citius.owl#ChooseOneOptionQuestion";
	private Option solution;

	public ChooseOneOptionQuestion() {
		super();
	}

	public ChooseOneOptionQuestion(String uri) {
		super(uri, false);
	}

	public ChooseOneOptionQuestion(String uri, boolean b) {
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
