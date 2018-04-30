package es.usc.citius.hmb.games;

public class InsertOneTextQuestion extends TextQuestionType {
	private static final long serialVersionUID = 1L;
	public static final String RDFS_CLASS = "http://citius.usc.es/hmb/citius.owl#InsertOneTextQuestion";
	private TextSolution solution;

	public InsertOneTextQuestion() {
		super();
	}

	public InsertOneTextQuestion(String uri) {
		super(uri, false);
	}

	public InsertOneTextQuestion(String uri, boolean b) {
		super(uri, false);
	}

	public void setSolution(TextSolution value) {
		this.solution = value;
	}

	public TextSolution getSolution() {
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
