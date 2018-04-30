package es.usc.citius.hmb.games;

public class TextSolution extends es.usc.citius.hmb.model.Sort {
	private static final long serialVersionUID = 1L;
	public static final String RDFS_CLASS = "http://citius.usc.es/hmb/citius.owl#TextSolution";
	private es.usc.citius.hmb.model.StringType solution;
	private es.usc.citius.hmb.model.IntegerType score;

	public TextSolution() {
		super();
	}

	public TextSolution(String uri) {
		super(uri, false);
	}

	public TextSolution(String uri, boolean b) {
		super(uri, false);
	}

	public void setSolution(es.usc.citius.hmb.model.StringType value) {
		this.solution = value;
	}

	public es.usc.citius.hmb.model.StringType getSolution() {
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
