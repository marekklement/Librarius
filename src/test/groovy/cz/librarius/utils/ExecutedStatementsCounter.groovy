package cz.librarius.utils

class ExecutedStatementsCounter extends SessionEventListenerAdapter {

    private int executedStatements = 0
    private int executedBatches = 0

    @Override
    void jdbcExecuteStatementEnd() {
        executedStatements++
    }

    @Override
    void jdbcExecuteBatchEnd() {
        executedBatches++
    }

    int getExecutedStatements() {
        return executedStatements
    }

    int getExecutedBatches() {
        return executedBatches
    }

    def verify(Assertion assertion) {
        def result = assertion.condition(executedStatements, executedBatches)
        assert result
        result
    }

    def verifyAndReset(Assertion assertion) {
        def result = verify(assertion)
        reset()
        result
    }

    static interface Assertion {
        boolean condition(int executedStatements, int executedBatches)
    }

    def reset() {
        executedStatements = 0
        executedBatches = 0
    }
}
