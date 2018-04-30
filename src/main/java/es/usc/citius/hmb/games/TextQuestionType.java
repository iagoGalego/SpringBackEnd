package es.usc.citius.hmb.games;

public class TextQuestionType extends AnswerType {
	private static final long serialVersionUID = 1L;
	public static final String RDFS_CLASS = "http://citius.usc.es/hmb/citius.owl#TextQuestionType";

	public TextQuestionType() {
		super();
	}

	public TextQuestionType(String uri) {
		super(uri, false);
	}

	public TextQuestionType(String uri, boolean b) {
		super(uri, false);
	}

    @Override
    public void genURI() {
        super.genURI();
        
    }
}
