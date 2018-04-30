package es.usc.citius.hmb.games;

public class Option extends es.usc.citius.hmb.model.Sort {
	private static final long serialVersionUID = 1L;
	public static final String RDFS_CLASS = "http://citius.usc.es/hmb/citius.owl#Option";
	private es.usc.citius.hmb.model.StringType text;
	private es.usc.citius.hmb.model.IntegerType score;

	public Option() {
		super();
	}

	public Option(String uri) {
		super(uri, false);
	}

	public Option(String uri, boolean b) {
		super(uri, false);
	}

	public void setText(es.usc.citius.hmb.model.StringType value) {
		this.text = value;
	}

	public es.usc.citius.hmb.model.StringType getText() {
		return this.text;
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
        
        if (this.text != null) {
            this.text.genURI();
        }

        if (this.score != null) {
            this.score.genURI();
        }

    }
}
