{
  description = "A very basic flake";

  outputs = { self, nixpkgs }: {

    # we're not actually building anything - this is just for the devShell really
    packages.x86_64-linux.hello = nixpkgs.legacyPackages.x86_64-linux.hello;
    defaultPackage.x86_64-linux = self.packages.x86_64-linux.hello;

    devShell.x86_64-linux = with import nixpkgs { system = "x86_64-linux"; };
      mkShell {
      buildInputs = [
        clojure
        clj-kondo
      ];

      shellHook = ''
        ~/.emacs.d/bin/doom env
      '';
    };
  };
}
