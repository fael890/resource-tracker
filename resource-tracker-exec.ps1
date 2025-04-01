Start-Process cmd -ArgumentList "/k java -jar resourcetracker-0.0.1-SNAPSHOT.jar" 
cd .\resource-tracker-GUI

$NpmNextDependencies = '.\.next'
$NpmNodeModule = '.\node_modules'

if ((Test-Path -Path $NpmNextDependencies) -and (Test-Path -Path $NpmNodeModule)) {
    npm run electron
} else {
    npm install
    npm run electron
}

cd ..