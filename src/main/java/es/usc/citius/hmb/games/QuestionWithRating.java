package es.usc.citius.hmb.games;

public class QuestionWithRating extends Question {
	private static final long serialVersionUID = 1L;
	public static final String RDFS_CLASS = "http://citius.usc.es/hmb/citius.owl#QuestionWithRating";
	private es.usc.citius.hmb.model.IntegerType rating;

	public QuestionWithRating() {
		super();
	}

	public QuestionWithRating(String uri) {
		super(uri, false);
	}

	public QuestionWithRating(String uri, boolean b) {
		super(uri, false);
	}

	public void setRating(es.usc.citius.hmb.model.IntegerType value) {
		this.rating = value;
	}

	public es.usc.citius.hmb.model.IntegerType getRating() {
		return this.rating;
	}

    @Override
    public void genURI() {
        super.genURI();
        
        if (this.rating != null) {
            this.rating.genURI();
        }

    }
}
