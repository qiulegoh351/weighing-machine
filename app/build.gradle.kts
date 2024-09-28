plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.apollo)
}

apollo {
    service("service") {
        // The package name for the generated models
        packageName.set("com.farmtech.weighningmachine")
        // Adds the given directory as a GraphQL source root
        srcDir("src/main/java/com/farmtech/weighingmachine/graphql")
        // Operation files to include.
        includes.add("**/*.graphql")
        // Operation files to exclude.
        excludes.add("**/*.graphqls")

        introspection {
            endpointUrl.set("https://api.unico-infotech.com/graphql-user")
            schemaFile.set(file("src/main/java/com/farmtech/weighingmachine/graphql/schema.graphqls"))
        }

//        // Explicitly set the schema
        schemaFiles.from("src/main/java/com/farmtech/weighingmachine/graphql/schema.graphqls")
//        // Extend your schema locally with type extensions
//        // schemaFiles.from("shared/graphql/schema.graphqls", "shared/graphql/extra.graphqls")
//
        // What codegen to use. One of "operationBased", "responseBased"
        codegenModels.set("operationBased")

//        // Warn if using a deprecated field
//        warnOnDeprecatedUsages.set(true)
//        // Fail on warnings
//        failOnWarnings.set(true)
//
//
        // The format to output for the operation manifest. One of "operationOutput", "persistedQueryManifest"
        operationManifestFormat.set("persistedQueryManifest")
        // The file where to write the operation manifest
        operationManifest.set(file("build/generated/persistedQueryManifest.json"))

        // Whether to generate Kotlin or Java models
        generateKotlinModels.set(true)
//        // Target language version for the generated code.
//        languageVersion.set("1.5")
        // Whether to suffix operation name with 'Query', 'Mutation' or 'Subscription'
        useSemanticNaming.set(true)
//        // Whether to generate kotlin constructors with `@JvmOverloads` for more graceful Java interop experience when default values are present.
//        addJvmOverloads.set(true)
//        // Whether to generate Kotlin models with `internal` visibility modifier.
//        generateAsInternal.set(true)
        // Whether to generate default implementation classes for GraphQL fragments.
        generateFragmentImplementations.set(true)
        // Whether to write the query document in models
        generateQueryDocument.set(true)
        // Whether to generate the Schema class.
        generateSchema.set(true)
        // Name for the generated schema
        generatedSchemaName.set("Schema")
        // Whether to generate operation variables as [com.apollographql.apollo.api.Optional]
        generateOptionalOperationVariables.set(true)
        // Whether to generate the type safe Data builders.
        generateDataBuilders.set(true)
        // Whether to generate response model builders for Java.
        // generateModelBuilders.set(true)
        // Which methods to auto generate (can include: `equalsHashCode`, `copy`, `toString`, or `dataClass`)
        generateMethods.set(listOf("dataClass"))
        // Whether to generate fields as primitive types (`int`, `double`, `boolean`) instead of their boxed types (`Integer`, `Double`, etc..)
        // generatePrimitiveTypes.set(true)
        // Opt-in Builders for Operations, Fragments and Input types. Builders are more ergonomic than default arguments when there are a lot of
        // optional arguments.
        generateInputBuilders.set(true)
        // When to add __typename.
        addTypename.set("always")
        // Whether to flatten the models. File paths are limited on MacOSX to 256 chars and flattening can help keeping the path length manageable
        // flattenModels.set(true)
        // A list of [Regex] patterns for GraphQL enums that should be generated as Kotlin sealed classes instead of the default Kotlin enums.
        // sealedClassesForEnumsMatching.set(listOf(".*"))
        // A list of [Regex] patterns for GraphQL enums that should be generated as Java classes.
        // classesForEnumsMatching.set(listOf(".*"))
        // Whether fields with different shape are disallowed to be merged in disjoint types.
        // fieldsOnDisjointTypesMustMerge.set(false)

        // Whether to generate Apollo metadata. Apollo metadata is used for multi-module support.
        generateApolloMetadata.set(true)
        // list of [Regex] patterns matching for types and fields that should be generated whether they are used by queries/fragments in this module or not.
        alwaysGenerateTypesMatching.set(listOf(".*"))
    }
}

android {
    namespace = "com.farmtech.weighingmachine"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.farmtech.weighingmachine"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.common.ktx)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.material3)
    implementation(libs.charts)
    implementation(libs.material3)
    implementation(libs.coil.compose)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.mpandroidchart)
    implementation(libs.kotlinx.coroutines.android)
    implementation(project.dependencies.platform(libs.koin.bom))
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
    implementation(libs.ui.test.junit4)
    implementation(libs.androidx.espresso.core)
    implementation(libs.styleabletoast)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.compose.bom)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.apollo.runtime)
    implementation(libs.androidx.security.crypto)
}