use std::collections::HashMap;
use std::env;
use std::error::Error;

#[macro_use]
extern crate clap;

fn request(host: &str, query: &str) -> Result<HashMap<String, String>, reqwest::Error> {
    reqwest::blocking::get(&format!("{}/fish/{}", host, query))?.json()
}

fn host(host: Option<&str>) -> Result<String, env::VarError> {
    match host {
        Some(v) => Ok(v.to_string()),
        None => env::var("HOST"),
    }
}

fn main() -> Result<(), Box<dyn Error>> {
    let matches = clap_app!(app =>
                            (version: "0.0.1")
                            (@arg HOST: -h --host +takes_value "Host destination")
                            (@subcommand fish =>
                             (about: "fish in different languages")
                             (@arg QUERY: +required "Query String")
                            )
    )
    .get_matches();

    let host = host(matches.value_of("HOST"))?;

    if let Some(matches) = matches.subcommand_matches("fish") {
        let query = matches.value_of("QUERY").unwrap();
        let body = request(&host, &query)?;

        println!("{:?}", body);
    }

    Ok(())
}
