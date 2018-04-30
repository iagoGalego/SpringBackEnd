package es.usc.citius.hmb.games;

public class VideoQuestionType extends QuestionType {
	private static final long serialVersionUID = 1L;
	public static final String RDFS_CLASS = "http://citius.usc.es/hmb/citius.owl#VideoQuestionType";
	private es.usc.citius.hmb.model.StringType videoURL;

	public VideoQuestionType() {
		super();
	}

	public VideoQuestionType(String uri) {
		super(uri, false);
	}

	public VideoQuestionType(String uri, boolean b) {
		super(uri, false);
	}

	public void setVideoURL(es.usc.citius.hmb.model.StringType value) {
		this.videoURL = value;
	}

	public es.usc.citius.hmb.model.StringType getVideoURL() {
		return this.videoURL;
	}

    @Override
    public void genURI() {
        super.genURI();
        
        if (this.videoURL != null) {
            this.videoURL.genURI();
        }

    }
}
