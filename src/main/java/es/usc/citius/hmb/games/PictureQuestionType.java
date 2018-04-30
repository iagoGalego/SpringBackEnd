package es.usc.citius.hmb.games;

public class PictureQuestionType extends QuestionType {
	private static final long serialVersionUID = 1L;
	public static final String RDFS_CLASS = "http://citius.usc.es/hmb/citius.owl#PictureQuestionType";
	private es.usc.citius.hmb.model.StringType imageURL;

	public PictureQuestionType() {
		super();
	}

	public PictureQuestionType(String uri) {
		super(uri, false);
	}

	public PictureQuestionType(String uri, boolean b) {
		super(uri, false);
	}

	public void setImageURL(es.usc.citius.hmb.model.StringType value) {
		this.imageURL = value;
	}

	public es.usc.citius.hmb.model.StringType getImageURL() {
		return this.imageURL;
	}

    @Override
    public void genURI() {
        super.genURI();
        
        if (this.imageURL != null) {
            this.imageURL.genURI();
        }

    }
}
