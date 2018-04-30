package es.usc.citius.hmb.games;

public class AnswerType extends es.usc.citius.hmb.model.Sort {
	private static final long serialVersionUID = 1L;
	public static final String RDFS_CLASS = "http://citius.usc.es/hmb/citius.owl#AnswerType";

	public AnswerType() {
		super();
	}

	public AnswerType(String uri) {
		super(uri, false);
	}

	public AnswerType(String uri, boolean b) {
		super(uri, false);
	}

    @Override
    public void genURI() {
        super.genURI();
        
    }
}
