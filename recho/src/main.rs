use std::collections::HashMap;
use std::env;
use std::error::Error;

#[macro_use]
extern crate clap;

fn request(host: &str, query: &str) -> Result<HashMap<String, String>, reqwest::Error> {
    reqwest::blocking::get(&format!("{}/fish/{}", host, query))?.json()
}

fn main() -> Result<(), Box<dyn Error>> {
    let matches = clap_app!(app =>
                            (version: "0.0.1")
                            (@subcommand fish =>
                             (about: "fish in different languages")
                             (@arg QUERY: +required "Query String")
                            )
    )
    .get_matches();

    if let Some(matches) = matches.subcommand_matches("fish") {
        let query = matches.value_of("QUERY").unwrap();

        let host = env::var("HOST")?;
        let body = request(&host, &query)?;

        println!("{:?}", body);
    }

    Ok(())
}
