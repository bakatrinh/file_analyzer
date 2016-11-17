package file_analyzer;

public class FileAnalyzerStringPairs {
	private String _pair = "";
	private int _sumOccurrences;
	
	public FileAnalyzerStringPairs(String pair, int sumOccurrences) {
		_pair = pair;
		_sumOccurrences = sumOccurrences;
	}
	
	public int getSumOccurrences() {
		return _sumOccurrences;
	}
	
	@Override
	public String toString() {
		return "[\""+_pair+"\","+_sumOccurrences+"]";
	}
}