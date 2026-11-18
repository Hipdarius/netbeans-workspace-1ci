param([switch]$AllProjects)
$ErrorActionPreference = 'Stop'
$root = Split-Path $PSScriptRoot -Parent
$projects = @('H1_TelephoneBook', 'H2_LinkedList', 'H3_LinkedListContinued',
    'H4_LinkedListImproved', 'H5_WordStatsClass')
if ($AllProjects) {
    $projects = @(Get-ChildItem $root -Directory | Where-Object {
        Test-Path (Join-Path $_.FullName 'src')
    } | Select-Object -ExpandProperty Name)
}
foreach ($project in $projects) {
    $folder = Join-Path $root $project
    $destination = Join-Path $folder 'build/study-check'
    New-Item -ItemType Directory -Force $destination | Out-Null
    $sources = @(Get-ChildItem (Join-Path $folder 'src') -Filter *.java -Recurse)
    $tests = @()
    if (Test-Path (Join-Path $folder 'test')) {
        $tests = @(Get-ChildItem (Join-Path $folder 'test') -Filter *.java -Recurse)
    }
    & javac -encoding UTF-8 -cp "$folder/lib/*" -d $destination @($sources.FullName) @($tests.FullName)
    if ($LASTEXITCODE -ne 0) { throw "Compilation failed: $project" }
    Write-Host "BUILD PASS: $project"
    foreach ($test in $tests) {
        $text = Get-Content -LiteralPath $test.FullName -Raw
        if ($text -notmatch 'public\s+static\s+void\s+main') { continue }
        $class = $test.BaseName
        if ($text -match '(?m)^package\s+([\w.]+)\s*;') { $class = $Matches[1] + '.' + $class }
        Push-Location $folder
        try {
            & java -cp "$destination;$folder/lib/*" $class
            if ($LASTEXITCODE -ne 0) { throw "Test failed: $project / $class" }
        } finally { Pop-Location }
    }
}
Write-Host 'All selected builds and automated tests passed.'
