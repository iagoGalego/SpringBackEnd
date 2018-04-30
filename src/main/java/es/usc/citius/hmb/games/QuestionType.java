package es.usc.citius.hmb.games;

public class QuestionType extends es.usc.citius.hmb.model.Sort {
	private static final long serialVersionUID = 1L;
	public static final String RDFS_CLASS = "http://citius.usc.es/hmb/citius.owl#QuestionType";

	public QuestionType() {
		super();
	}

	public QuestionType(String uri) {
		super(uri, false);
	}

	public QuestionType(String uri, boolean b) {
		super(uri, false);
	}

    @Override
    public void genURI() {
        super.genURI();
        
    }
}
