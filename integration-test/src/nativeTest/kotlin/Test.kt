import kotlin.system.exitProcess
import org.spekframework.spek2.integration.CalculatorSpecs
import org.spekframework.spek2.integration.EmptyGroupTest
import org.spekframework.spek2.integration.MemoizedTests
import org.spekframework.spek2.integration.NonUniquePathTest
import org.spekframework.spek2.integration.SetFeature
import org.spekframework.spek2.integration.SetSpec
import org.spekframework.spek2.integration.SkipTest
import org.spekframework.spek2.runtime.SpekRuntime
import org.spekframework.spek2.runtime.execution.addClass
import org.spekframework.spek2.runtime.execution.ConsoleExecutionListener
import org.spekframework.spek2.runtime.execution.DiscoveryContext
import org.spekframework.spek2.runtime.execution.DiscoveryRequest
import org.spekframework.spek2.runtime.execution.ExecutionRequest

fun main() {
    val discoveryContext = DiscoveryContext.builder()
            .addClass { CalculatorSpecs }
            .addClass { EmptyGroupTest }
            .addClass { CalculatorSpecs }
            .addClass { MemoizedTests }
            .addClass { NonUniquePathTest }
            .addClass { SetFeature }
            .addClass { SetSpec }
            .addClass { SkipTest }
            .build()

    val runtime = SpekRuntime()
    val discoveryRequest = DiscoveryRequest(discoveryContext)
    val discoveryResult = runtime.discover(discoveryRequest)

    val listener = ConsoleExecutionListener()
    val executionRequest = ExecutionRequest(discoveryResult.roots, listener)
    runtime.execute(executionRequest)

    if (!listener.wasSuccessful) {
        exitProcess(-1)
    }
}
