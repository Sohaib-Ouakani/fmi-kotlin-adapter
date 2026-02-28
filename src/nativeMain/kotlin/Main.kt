import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.toKString
import libfmi.*

const val DIR = "./resources/"


@OptIn(ExperimentalForeignApi::class)
fun main() {
    val context: CPointer<fmi_import_context_t>? = fmi_import_allocate_context(null)
    val verison = fmi_import_get_fmi_version(
        context,
        "./resources/BouncingBall.fmu",
        "./resources/"
    )
    val characteristics: MutableMap<String, String?> = mutableMapOf()

    val fmi = fmi2_import_parse_xml(context, DIR, null)

    characteristics["Model Name"] = fmi2_import_get_model_name(fmi)?.toKString()
    characteristics["Description"] = fmi2_import_get_description(fmi)?.toKString()
    characteristics["Author"] = fmi2_import_get_author(fmi)?.toKString()
    characteristics["FMU Version"] = fmi2_import_get_model_version(fmi)?.toKString()
    characteristics["Default Experiment Start"] = fmi2_import_get_default_experiment_start(fmi).toString()
    characteristics["Default Experiment Stop"] = fmi2_import_get_default_experiment_stop(fmi).toString()
    characteristics["Default Experiment Step"] = fmi2_import_get_default_experiment_step(fmi).toString()
    characteristics["FMU type"] = fmi2_import_get_fmu_kind(fmi).toString()

    print(characteristics)
}
