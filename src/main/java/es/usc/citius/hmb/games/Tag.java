package es.usc.citius.hmb.games;

public class Tag extends es.usc.citius.hmb.model.Sort {
	private static final long serialVersionUID = 1L;
	public static final String RDFS_CLASS = "http://citius.usc.es/hmb/citius.owl#Tag";
	private es.usc.citius.hmb.model.StringType value;

	public Tag() {
		super();
	}

	public Tag(String uri) {
		super(uri, false);
	}

	public Tag(String uri, boolean b) {
		super(uri, false);
	}

	public void setValue(es.usc.citius.hmb.model.StringType value) {
		this.value = value;
	}

	public es.usc.citius.hmb.model.StringType getValue() {
		return this.value;
	}

    @Override
    public void genURI() {
        super.genURI();
        
        if (this.value != null) {
            this.value.genURI();
        }

    }
}
