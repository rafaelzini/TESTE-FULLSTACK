import { AngularEditorConfig } from "@kolkov/angular-editor";

export class TextEditorConfig {

    static readonly globalConfig: AngularEditorConfig = {
        editable: true,
        spellcheck: true,
        height: '15rem',
        minHeight: '5rem',
        placeholder: 'Anotações...',
        translate: 'no',
        defaultParagraphSeparator: 'p',
        defaultFontName: 'Arial',
        toolbarHiddenButtons: [
            ['bold']
        ],
        customClasses: [
            {
                name: "quote",
                class: "quote",
            },
            {
                name: 'redText',
                class: 'redText'
            },
            {
                name: "titleText",
                class: "titleText",
                tag: "h1",
            },
        ]
    };

}